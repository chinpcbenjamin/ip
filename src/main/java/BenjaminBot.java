import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BenjaminBot {
    private final Ui ui;

    public BenjaminBot(Ui ui) {
        this.ui = ui;
    }

    public enum TaskActionType {
        ADD,
        REMOVE
    }

    public static void loadSavedTasks(String s, ArrayList<Task> arr) {
        String[] stringArray = s.split(",");
        switch (stringArray[0]) {
        case "T":
            arr.add(new Todo(stringArray[2], stringArray[1].equals("1")));
            break;
        case "D":
            arr.add(new Deadline(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3])));
            break;
        case "E":
            arr.add(new Event(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3]), LocalDateTime.parse(stringArray[4])));
        }
    }

    public static void main(String[] args) {
        BenjaminBot benjaminBot = new BenjaminBot(new Ui());
        ArrayList<Task> taskArr = new ArrayList<>(100);
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
                loadSavedTasks(reader.nextLine(), taskArr);
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
                for (int i = 0; i < taskArr.size(); i++) {
                    System.out.println(i + 1 + ". " + taskArr.get(i));
                }
            } else if (s.startsWith("mark")) {
                benjaminBot.ui.handleMark(s, taskArr);
            } else if (s.startsWith("unmark")) {
                benjaminBot.ui.handleUnmark(s, taskArr);
            } else if (s.startsWith("todo")) {
                benjaminBot.ui.handleTodo(s, taskArr);
            } else if (s.startsWith("deadline")) {
                benjaminBot.ui.handleDeadline(s, taskArr);
            } else if (s.startsWith("event")) {
                benjaminBot.ui.handleEvent(s, taskArr);
            } else if (s.startsWith("delete")) {
                benjaminBot.ui.handleDelete(s, taskArr);
            }

            else {
                System.out.println("Hey! I do not understand that. Please try something else!");
            }
            benjaminBot.ui.printDivider();
            s = sc.nextLine();
            benjaminBot.ui.printDivider();
        }

        try {
            FileWriter writer = new FileWriter(savedTasks);
            for (Task task : taskArr) {
                writer.write(task.saveAsString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save your tasks! " + e.getMessage());
        }

        System.out.println("Bye. Hope to see you again soon!");
        benjaminBot.ui.printDivider();
    }
}
