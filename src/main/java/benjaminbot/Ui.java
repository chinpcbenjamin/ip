package benjaminbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the interface through which a human user will receive responses from.
 */
public class Ui {
    /**
     * Returns the welcome message that a user will see upon starting the bot.
     *
     * @return Welcome message that a user will see upon starting the bot.
     */
    public String welcomeMessage() {
        return "Hello! I am BenjaminBot! What can I do for you today?";
    }

    /**
     * Prints the task that the user has currently added or deleted, as a confirmation message.
     *
     * @param t Task that the user is adding or deleting.
     * @param size Integer representing the current number of tasks that the BenjaminBot is saving.
     * @param act The variable specifying whether the user is adding or deleting a task.
     */
    public String taskPrint(Task t, int size, BenjaminBot.TaskActionType act) {
        String returnString = "";
        switch (act) {
        case TASK_ACTION_TYPE_ADD:
            returnString += "Got it. I've added this task:\n";
            break;
        case TASK_ACTION_TYPE_REMOVE:
            returnString += "Noted. I've removed this task:\n";
            break;
        default:
            break;
        }
        returnString += "  " + t + "\n";
        returnString += "Now you have " + size + " tasks in the list.";

        return returnString;
    }

    /**
     * Tells the TaskList instance to mark a task as done, and prints out a confirmation.
     *
     * @param s String representing the command that specifies the index to mark.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleMark(String s, TaskList arr) {
        assert s.startsWith("mark") : "String command should start with mark for a mark command";
        try {
            int count = Integer.parseInt(s.substring(5)) - 1;
            arr.markTask(count);
            return "Nice! I've marked this task as done:\n  "
                    + arr.getTask(count);

        } catch (NumberFormatException e) {
            return "ERROR! Your formatting of 'mark' is wrong! The correct format is: mark 'integer'\n"
                    + "For example, 'mark 5' sets the 5th item in the list to be done.\n"
                    + "Remember, that the integer should not exceed the number of items you have listed";
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! You do not have that many items in your list!\n"
                    + "Your formatting of 'mark' is right, but please enter a valid integer\n"
                    + "Remember, that the integer should not exceed the number of items you have listed";
        }
    }

    /**
     * Tells the TaskList instance to mark a task as not done, and prints out a confirmation.
     *
     * @param s String representing the command that specifies the index to unmark.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleUnmark(String s, TaskList arr) {
        assert s.startsWith("unmark") : "String command should start with unmark for a unmark command";
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            arr.unmarkTask(count);
            return "Ok, I've marked this task as not done yet:\n  "
                    + arr.getTask(count);
        } catch (NumberFormatException e) {
            return "ERROR! Your formatting of 'unmark' is wrong! The correct format is: unmark 'integer'\n"
                    + "For example, 'unmark 5' sets the 5th item in the list to be not done.\n"
                    + "Remember, that the integer should not exceed the number of items you have listed";
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! You do not have that many items in your list!\n"
                    + "Your formatting of 'unmark' is right, but please enter a valid integer\n"
                    + "Remember, that the integer should not exceed the number of items you have listed";
        }
    }

    /**
     * Tells the TaskList instance add this new todo, and prints out a confirmation.
     *
     * @param s String representing the command containing the todo.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleTodo(String s, TaskList arr) {
        assert s.startsWith("todo") : "String command should start with todo for a todo command";
        try {
            Task t = new Todo(s.substring(5));
            arr.addTask(t);
            return taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! You need to specify a todo! The correct format is: todo 'description'\n"
                + "For example, 'todo read book' enters a new todo called 'read book'";
        }
    }

    /**
     * Tells the TaskList instance add this new deadline, and prints out a confirmation.
     *
     * @param s String representing the command containing the deadline.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleDeadline(String s, TaskList arr) {
        assert s.startsWith("deadline") : "String command should start with deadline for a deadline command";
        try {
            int slashIndex = s.indexOf("/by");
            Task t = new Deadline(
                    s.substring(9, slashIndex - 1),
                    LocalDateTime.parse(s.substring(slashIndex + 4)));
            arr.addTask(t);
            return taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! Your formatting of your 'deadline' is wrong! You do not have a '/by'!\n"
                + "The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS";
        } catch (DateTimeParseException e) {
            return "ERROR! Your time is not formatted correctly!\n"
                + "The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'\n"
                + "The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS'";
        }
    }

    /**
     * Tells the TaskList instance add this new event, and prints out a confirmation.
     *
     * @param s String representing the command containing the event.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleEvent(String s, TaskList arr) {
        assert s.startsWith("event") : "String command should start with event for an event command";
        try {
            int slashIndex = s.indexOf("/from");
            int slashIndexTwo = s.indexOf("/to", slashIndex + 1);
            Task t = new Event(
                    s.substring(6, slashIndex - 1),
                    LocalDateTime.parse(s.substring(slashIndex + 6, slashIndexTwo - 1)),
                    LocalDateTime.parse(s.substring(slashIndexTwo + 4)));
            arr.addTask(t);
            return taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! Your formatting of your 'event' is wrong!\n"
                + "You need both a '/from' and a '/to'\n"
                + "The correct format is: event 'description' "
                + "/from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'";
        } catch (DateTimeParseException e) {
            return "ERROR! Your time is not formatted correctly!\n"
                + "The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'"
                + "The correct format is: event 'description' "
                + "/from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'";
        }
    }

    /**
     * Tells the TaskList instance add this delete the task, and prints out a confirmation.
     *
     * @param s String representing that specifies the index to delete.
     * @param arr The TaskList instance that contains the task.
     */
    public String handleDelete(String s, TaskList arr) {
        assert s.startsWith("delete") : "String command should start with delete for a delete command";
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            Task t = arr.removeTask(count);
            return taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_REMOVE);
        } catch (NumberFormatException e) {
            return "ERROR! Your formatting of 'delete' is wrong! The correct format is: delete 'integer'\n"
                + "For example, 'delete 5' removes the 5th item in the list.\n"
                + "Remember, that the integer should not exceed the number of items you have listed";
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! You do not have that many items in your list!\n"
                + "Your formatting of 'delete' is right, but please enter a valid integer\n"
                + "Remember, that the integer should not exceed the number of items you have listed";
        }
    }

    /**
     * Prints out the current tasks stored by the TaskList.
     *
     * @param arr The TaskList containing the tasks to be printed out.
     */
    public String handleList(TaskList arr) {
        if (arr.getTaskCount() == 0) {
            return "You have not added any tasks! Try adding a task first!";
        }

        StringBuilder tasks = new StringBuilder();
        tasks.append("Here are the tasks in your list:\n");
        for (int i = 0; i < arr.getTaskCount(); i++) {
            tasks.append(i + 1).append(". ").append(arr.getTask(i)).append("\n");
        }
        return tasks.toString();
    }

    /**
     * Prints the message that users will see when the bot is closing.
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the message that users will see when they have entered an invalid command.
     */
    public String invalidCommandMessage() {
        return "Hey! I do not understand that. Please try something else!";
    }

    /**
     * Prints out all tasks that contain the string keyword.
     *
     * @param s String containing the keyword to be searched for.
     * @param arr TaskList containing the tasks.
     */
    public String handleFind(String s, TaskList arr) {
        ArrayList<Task> list = arr.findTasksContainingKeyword(s.substring(5));
        if (list.isEmpty()) {
            return "Sorry! I couldn't find any task containing that keyword";
        } else {
            String returnString = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                returnString += (i + 1) + ". " + list.get(i) + "\n";
            }
            return returnString;

        }
    }

    /**
     * Prints out all tasks that happen on the date.
     *
     * @param s String containing the date to check for.
     * @param arr TaskList containing the tasks.
     */
    public String handleView(String s, TaskList arr) {
        assert s.startsWith("view") : "String command should start with view for a view command";
        try {
            String dateString = s.substring(5);
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            ArrayList<Task> matches = arr.findTasksOnDate(date);

            if (matches.isEmpty()) {
                return "Your schedule for " + date + " is empty. Add something to it!";
            }

            StringBuilder returnString = new StringBuilder();
            returnString.append("Here is the schedule for: ").append(date).append("\n");
            for (int i = 0; i < matches.size(); i++) {
                returnString.append(i + 1).append(". ").append(arr.getTask(i)).append("\n");
            }
            return returnString.toString();
        } catch (IndexOutOfBoundsException e) {
            return "ERROR! Your formatting of your 'view' is wrong!\n"
                    + "The correct format is: view 'YYYY-MM-DD'";
        }catch (DateTimeParseException e) {
            return "ERROR! Your time is not formatted correctly!\n"
                    + "The correct format for a time is: 'YYYY-MM-DD'\n"
                    + "The correct format is: view 'YYYY-MM-DD'\n" + s.substring(5);
        }
    }
}
