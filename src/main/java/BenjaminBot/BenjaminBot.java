package BenjaminBot;

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
        TASK_ACTION_TYPE_ADD,
        TASK_ACTION_TYPE_REMOVE
    }

    public void exit() {
        this.storage.writeToStorage(this.taskArr);
        this.ui.byeMessage();
        this.ui.printDivider();
        System.exit(0);
    }

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
