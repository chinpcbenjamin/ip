package benjaminbot;

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
    private Parser parser;

    /**
     * Constructs a BenjaminBot instance, given instances of Ui, TaskList and Storage.
     *
     * @param ui The user interface for the program to interact with the user.
     * @param taskArr The list of tasks currently being stored.
     * @param storage The storage object for loading and saving tasks.
     */
    public BenjaminBot(Ui ui, TaskList taskArr, Storage storage) {
        this.ui = ui;
        this.taskArr = taskArr;
        this.storage = storage;
        this.storage.load(taskArr);
        this.parser = new Parser();
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
    public String exit() {
        this.storage.writeToStorage(this.taskArr);
        return this.ui.byeMessage();
    }

    public String getWelcomeMessage() {
        return this.ui.welcomeMessage();
    }

    public String getResponse(String s) {
        return parser.parse(
                s,
                this.ui,
                this.taskArr,
                this);
    }
}
