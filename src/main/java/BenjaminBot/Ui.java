package BenjaminBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the interface through which a human user will receive responses from.
 */
public class Ui {
    /**
     * Prints a dotted line that represents a divider between responses.
     */
    public void printDivider() {
        System.out.println("___________________________________________________________________");
    }

    /**
     * Prints the welcome message that a user will see upon starting the bot.
     */
    public void welcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints the task that the user has currently added or deleted, as a confirmation message.
     * @param t Task that the user is adding or deleting.
     * @param size Integer representing the current number of tasks that the BenjaminBot is saving.
     * @param act The variable specifying whether the user is adding or deleting a task.
     */
    public void taskPrint(Task t, int size, BenjaminBot.TaskActionType act) {
        switch (act) {
        case TASK_ACTION_TYPE_ADD:
            System.out.println("Got it. I've added this task:");
            break;
        case TASK_ACTION_TYPE_REMOVE:
            System.out.println("Noted. I've removed this task:");
            break;
        }
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Tells the TaskList instance to mark a task as done, and prints out a confirmation.
     * @param s String representing the command that specifies the index to mark.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleMark(String s, TaskList arr) {
        try {
            int count = Integer.parseInt(s.substring(5)) - 1;
            arr.markTask(count);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + arr.getTask(count));
        } catch (NumberFormatException e) {
            System.out.println("ERROR! Your formatting of 'mark' is wrong! The correct format is: mark 'integer'");
            System.out.println("For example, 'mark 5' sets the 5th item in the list to be done.");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You do not have that many items in your list!");
            System.out.println("Your formatting of 'mark' is right, but please enter a valid integer");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        }
    }

    /**
     * Tells the TaskList instance to mark a task as not done, and prints out a confirmation.
     * @param s String representing the command that specifies the index to unmark.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleUnmark(String s, TaskList arr) {
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            arr.unmarkTask(count);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println("  " + arr.getTask(count));
        } catch (NumberFormatException e) {
            System.out.println("ERROR! Your formatting of 'unmark' is wrong! The correct format is: unmark 'integer'");
            System.out.println("For example, 'unmark 5' sets the 5th item in the list to be not done.");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You do not have that many items in your list!");
            System.out.println("Your formatting of 'unmark' is right, but please enter a valid integer");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        }
    }

    /**
     * Tells the TaskList instance add this new todo, and prints out a confirmation.
     * @param s String representing the command containing the todo.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleTodo(String s, TaskList arr) {
        try {
            Task t = new Todo(s.substring(5));
            arr.addTask(t);
            taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You need to specify a todo! The correct format is: todo 'description'");
            System.out.println("For example, 'todo read book' enters a new todo called 'read book'");
        }
    }

    /**
     * Tells the TaskList instance add this new deadline, and prints out a confirmation.
     * @param s String representing the command containing the deadline.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleDeadline(String s, TaskList arr) {
        try {
            int slashIndex = s.indexOf("/by");
            Task t = new Deadline(
                    s.substring(9, slashIndex - 1),
                    LocalDateTime.parse(s.substring(slashIndex + 4)));
            arr.addTask(t);
            taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'deadline' is wrong! You do not have a '/by'!");
            System.out.println("The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS");
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Your time is not formatted correctly!");
            System.out.println("The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'");
            System.out.println("The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS'");
        }
    }

    /**
     * Tells the TaskList instance add this new event, and prints out a confirmation.
     * @param s String representing the command containing the event.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleEvent(String s, TaskList arr) {
        try {
            int slashIndex = s.indexOf("/from");
            int slashIndexTwo = s.indexOf("/to", slashIndex + 1);
            Task t = new Event(
                    s.substring(6, slashIndex - 1),
                    LocalDateTime.parse(s.substring(slashIndex + 6, slashIndexTwo - 1)),
                    LocalDateTime.parse(s.substring(slashIndexTwo + 4)));
            arr.addTask(t);
            taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'event' is wrong!");
            System.out.println("You need both a '/from' and a '/to'");
            System.out.println("The correct format is: event 'description' "
                    + "/from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'");
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Your time is not formatted correctly!");
            System.out.println("The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'");
            System.out.println("The correct format is: event 'description' "
                    + "/from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'");
        }
    }

    /**
     * Tells the TaskList instance add this delete the task, and prints out a confirmation.
     * @param s String representing that specifies the index to delete.
     * @param arr The TaskList instance that contains the task.
     */
    public void handleDelete(String s, TaskList arr) {
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            Task t = arr.removeTask(count);
            taskPrint(t, arr.getTaskCount(), BenjaminBot.TaskActionType.TASK_ACTION_TYPE_REMOVE);
        } catch (NumberFormatException e) {
            System.out.println("ERROR! Your formatting of 'delete' is wrong! The correct format is: delete 'integer'");
            System.out.println("For example, 'delete 5' removes the 5th item in the list.");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You do not have that many items in your list!");
            System.out.println("Your formatting of 'delete' is right, but please enter a valid integer");
            System.out.println("Remember, that the integer should not exceed the number of items you have listed");
        }
    }

    /**
     * Prints out the current tasks stored by the TaskList.
     * @param arr The TaskList containing the tasks to be printed out.
     */
    public void handleList(TaskList arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.getTaskCount(); i++) {
            System.out.println(i + 1 + ". " + arr.getTask(i));
        }
    }

    /**
     * Prints the message that users will see when the bot is closing.
     */
    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the message that users will see when they have entered an invalid command.
     */
    public void invalidCommandMessage() {
        System.out.println("Hey! I do not understand that. Please try something else!");
    }

    /**
     * Prints out all tasks that contain the string keyword.
     * @param s String containing the keyword to be searched for.
     * @param arr TaskList containing the tasks.
     */
    public void handleFind(String s, TaskList arr) {
        ArrayList<Task> list = arr.findTasksContainingKeyword(s.substring(5));
        if (list.isEmpty()) {
            System.out.println("Sorry! I couldn't find any task containing that keyword");
        } else {
            System.out.println("Here ate the matching tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i));
            }
        }
    }
}
