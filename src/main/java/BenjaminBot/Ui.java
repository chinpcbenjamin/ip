package BenjaminBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Ui {
    public void printDivider() {
        System.out.println("___________________________________________________________________");
    }

    public void welcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        printDivider();
    }

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

    public void handleList(TaskList arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.getTaskCount(); i++) {
            System.out.println(i + 1 + ". " + arr.getTask(i));
        }
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void invalidCommandMessage() {
        System.out.println("Hey! I do not understand that. Please try something else!");
    }
}
