package BenjaminBot;

import java.util.Scanner;

/**
 * Represents the main entry point to the BenjaminBot program.
 * It initialises its own Ui, TaskList and Storage to be used during execution.
 * It also handles the main loop of the program which takes in user input.
 */
public class BenjaminBot {
    private final Ui ui;
    private TaskList taskArr;
    private Storage storage;

    /**
     * Constructs a BenjaminBot instance, given instances of Ui, TaskList and Storage.
     * @param ui The user interface for the program to interact with the user.
     * @param taskArr The list of tasks currently being stored.
     * @param storage The storage object for loading and saving tasks.
     */
    public BenjaminBot(Ui ui, TaskList taskArr, Storage storage) {
        this.ui = ui;
        this.taskArr = taskArr;
        this.storage = storage;
    }

    /**
     * An enum representing the two types of actions that a task can have.
     */
    public enum TaskActionType {
        TASK_ACTION_TYPE_ADD,
        TASK_ACTION_TYPE_REMOVE
    }

    /**
     * Saves the tasks in the current TaskList into a file that is specified by the Storage.
     * Then, exits the application.
     */
    public void exit() {
        this.storage.writeToStorage(this.taskArr);
        this.ui.byeMessage();
        this.ui.printDivider();
        System.exit(0);
    }

    /**
     * Serves as the entry point to the application.
     * @param args Unused in this implementation.
     */
    public static void main(String[] args) {
        BenjaminBot benjaminBot = new BenjaminBot(
                new Ui(),
                new TaskList(),
                new Storage("./data/benjamin.txt"));

        benjaminBot.ui.welcomeMessage();

        benjaminBot.storage.load(benjaminBot.taskArr);

        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        benjaminBot.ui.printDivider();

        while (!s.isEmpty()) {
            parser.parse(
                    s,
                    benjaminBot.ui,
                    benjaminBot.taskArr,
                    benjaminBot);

            benjaminBot.ui.printDivider();
            s = sc.nextLine();
            benjaminBot.ui.printDivider();
        }
    }
}
