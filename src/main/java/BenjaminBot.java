import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BenjaminBot {
    private final Ui ui;
    private TaskList taskArr;
    private Storage storage;

    public BenjaminBot(Ui ui, TaskList taskArr, Storage storage) {
        this.ui = ui;
        this.taskArr = taskArr;
        this.storage = storage;
    }

    public enum TaskActionType {
        ADD,
        REMOVE
    }

    public static void main(String[] args) {
        BenjaminBot benjaminBot = new BenjaminBot(new Ui(), new TaskList(), new Storage("./data/benjamin.txt"));
        benjaminBot.ui.welcomeMessage();

        benjaminBot.storage.load(benjaminBot.taskArr);

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

        benjaminBot.storage.writeToStorage(benjaminBot.taskArr);

        System.out.println("Bye. Hope to see you again soon!");
        benjaminBot.ui.printDivider();
    }
}
