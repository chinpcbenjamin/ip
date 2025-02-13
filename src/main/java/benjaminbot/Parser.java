package benjaminbot;

/**
 * Represents the object that helps to decode the commands from user inputs
 */
public class Parser {
    /**
     * Decodes a string user input, the calls the relevant functions that are meant to handle such user commands.
     *
     * @param s The command input by the user.
     * @param ui The Ui instance that this parse will pass comments on to.
     * @param taskArr The TaskList instance that contains the current tasks of the BenjaminBot instance.
     * @param benjaminBot The current instance of BenjaminBot that uses this parser.
     */
    public String parse(String s, Ui ui, TaskList taskArr, BenjaminBot benjaminBot) {
        if (s.equals("list")) {
            return ui.handleList(taskArr);
        } else if (s.equals("bye")) {
            return benjaminBot.exit();
        } else if (s.startsWith("mark")) {
            return ui.handleMark(s, taskArr);
        } else if (s.startsWith("unmark")) {
            return ui.handleUnmark(s, taskArr);
        } else if (s.startsWith("todo")) {
            return ui.handleTodo(s, taskArr);
        } else if (s.startsWith("deadline")) {
            return ui.handleDeadline(s, taskArr);
        } else if (s.startsWith("event")) {
            return ui.handleEvent(s, taskArr);
        } else if (s.startsWith("delete")) {
            return ui.handleDelete(s, taskArr);
        } else if (s.startsWith("find")) {
            return ui.handleFind(s, taskArr);
        } else {
            return ui.invalidCommandMessage();
        }
    }
}
