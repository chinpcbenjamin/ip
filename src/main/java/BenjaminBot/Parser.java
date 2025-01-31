package BenjaminBot;

public class Parser {
    public void parse(String s, Ui ui, TaskList taskArr, BenjaminBot benjaminBot) {
        if (s.equals("list")) {
            ui.handleList(taskArr);
        } else if (s.equals("bye")) {
            benjaminBot.exit();
        } else if (s.startsWith("mark")) {
            ui.handleMark(s, taskArr);
        } else if (s.startsWith("unmark")) {
            ui.handleUnmark(s, taskArr);
        } else if (s.startsWith("todo")) {
            ui.handleTodo(s, taskArr);
        } else if (s.startsWith("deadline")) {
            ui.handleDeadline(s, taskArr);
        } else if (s.startsWith("event")) {
            ui.handleEvent(s, taskArr);
        } else if (s.startsWith("delete")) {
            ui.handleDelete(s, taskArr);
        } else {
            ui.invalidCommandMessage();
        }
    }
}
