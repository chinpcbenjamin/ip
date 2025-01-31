import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BenjaminBot {
    private final Ui ui;
    private TaskList taskArr;

    public BenjaminBot(Ui ui, TaskList taskArr) {
        this.ui = ui;
        this.taskArr = taskArr;
    }

    public enum TaskActionType {
        ADD,
        REMOVE
    }

    public static void loadSavedTasks(String s, TaskList arr) {
        String[] stringArray = s.split(",");
        switch (stringArray[0]) {
        case "T":
            arr.addTask(new Todo(stringArray[2], stringArray[1].equals("1")));
            break;
        case "D":
            arr.addTask(new Deadline(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3])));
            break;
        case "E":
            arr.addTask(new Event(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3]), LocalDateTime.parse(stringArray[4])));
        }
    }

    public static void main(String[] args) {
        BenjaminBot benjaminBot = new BenjaminBot(new Ui(), new TaskList());
        benjaminBot.ui.welcomeMessage();

        File savedTasks = new File("./data/benjamin.txt");
        if (!savedTasks.exists()) {
            try {
                new File("./data/").mkdirs();
                savedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println("Sorry, error getting file storage ! " + e.getMessage());
                return;
            }
        }

        try {
            Scanner reader = new Scanner(savedTasks);
            while (reader.hasNext()) {
                loadSavedTasks(reader.nextLine(), benjaminBot.taskArr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, issue reading file " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        benjaminBot.ui.printDivider();

        while (!s.equals("bye")) {
            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < benjaminBot.taskArr.getTaskCount(); i++) {
                    System.out.println(i + 1 + ". " + benjaminBot.taskArr.getTask(i));
                }
            } else if (s.startsWith("mark")) {
                benjaminBot.ui.handleMark(s, benjaminBot.taskArr);
            } else if (s.startsWith("unmark")) {
                benjaminBot.ui.handleUnmark(s, benjaminBot.taskArr);
            } else if (s.startsWith("todo")) {
                benjaminBot.ui.handleTodo(s, benjaminBot.taskArr);
            } else if (s.startsWith("deadline")) {
                benjaminBot.ui.handleDeadline(s, benjaminBot.taskArr);
            } else if (s.startsWith("event")) {
                benjaminBot.ui.handleEvent(s, benjaminBot.taskArr);
            } else if (s.startsWith("delete")) {
                benjaminBot.ui.handleDelete(s, benjaminBot.taskArr);
            }

            else {
                System.out.println("Hey! I do not understand that. Please try something else!");
            }
            benjaminBot.ui.printDivider();
            s = sc.nextLine();
            benjaminBot.ui.printDivider();
        }

        benjaminBot.taskArr.writeToStorage(savedTasks);

        System.out.println("Bye. Hope to see you again soon!");
        benjaminBot.ui.printDivider();
    }
}
