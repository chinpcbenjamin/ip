import java.util.ArrayList;
import java.util.Scanner;

public class BenjaminBot {
    public static void printDivider() {
        System.out.println("___________________________________________________________________");
    }

    public static void welcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        printDivider();

    }

    public static void addTaskPrint(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void handleMark(String s, ArrayList<Task> arr) {
        try {
            int count = Integer.parseInt(s.substring(5)) - 1;
            Task t = arr.get(count);
            t.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + t);
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

    public static void handleUnmark(String s, ArrayList<Task> arr) {
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            Task t = arr.get(count);
            t.setNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println("  " + t);
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

    public static void handleTodo(String s, ArrayList<Task> arr) {
        try {
            Task t = new Todo(s.substring(5));
            arr.add(t);
            addTaskPrint(t, arr.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You need to specify a todo! The correct format is: todo 'description'");
            System.out.println("For example, 'todo read book' enters a new todo called 'read book'");
        }
    }

    public static void handleDeadline(String s, ArrayList<Task> arr) {
        try {
            int slashIndex = s.indexOf("/by");
            Task t = new Deadline(s.substring(9, slashIndex - 1), s.substring(slashIndex + 4));
            arr.add(t);
            addTaskPrint(t, arr.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'deadline' is wrong! You do not have a '/by'!");
            System.out.println("The correct format is: deadline 'description' /by 'time'");
            System.out.println("For example, 'deadline return book /by Sunday' is a valid entry");
        }
    }

    public static void handleEvent(String s, ArrayList<Task> arr) {
        try {
            int slashIndex = s.indexOf("/from");
            int slashIndexTwo = s.indexOf("/to", slashIndex + 1);
            Task t = new Event(s.substring(6, slashIndex - 1),
                    s.substring(slashIndex + 6, slashIndexTwo - 1),
                    s.substring(slashIndexTwo + 4));
            arr.add(t);
            addTaskPrint(t, arr.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'event' is wrong!");
            System.out.println("You need both a '/from' and a '/to'");
            System.out.println("The correct format is: event 'description' /from 'time' /to 'time'");
            System.out.println("For example, 'event project meeting /from Mon 2pm /to 4pm' is a valid entry");
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> taskArr = new ArrayList<>(100);
        welcomeMessage();

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        printDivider();

        while (!s.equals("bye")) {
            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskArr.size(); i++) {
                    System.out.println(i + 1 + ". " + taskArr.get(i));
                }
            } else if (s.startsWith("mark")) {
                handleMark(s, taskArr);
            } else if (s.startsWith("unmark")) {
                handleUnmark(s, taskArr);
            } else if (s.startsWith("todo")) {
                handleTodo(s, taskArr);
            } else if (s.startsWith("deadline")) {
                handleDeadline(s, taskArr);
            } else if (s.startsWith("event")) {
                handleEvent(s, taskArr);
            } else {
                System.out.println("Hey! I do not understand that. Please try something else!");
            }
            printDivider();
            s = sc.nextLine();
            printDivider();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }
}
