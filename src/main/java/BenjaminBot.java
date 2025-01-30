import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class BenjaminBot {
    public enum TaskActionType {
        ADD,
        REMOVE
    }
    public static void printDivider() {
        System.out.println("___________________________________________________________________");
    }

    public static void welcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        printDivider();

    }

    public static void taskPrint(Task t, int size, TaskActionType act) {
        switch (act) {
            case ADD:
                System.out.println("Got it. I've added this task:");
                break;
            case REMOVE:
                System.out.println("Noted. I've removed this task:");
                break;
        }
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
            taskPrint(t, arr.size(), TaskActionType.ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! You need to specify a todo! The correct format is: todo 'description'");
            System.out.println("For example, 'todo read book' enters a new todo called 'read book'");
        }
    }

    public static void handleDeadline(String s, ArrayList<Task> arr) {
        try {
            int slashIndex = s.indexOf("/by");
            Task t = new Deadline(s.substring(9, slashIndex - 1), LocalDateTime.parse(s.substring(slashIndex + 4)));
            arr.add(t);
            taskPrint(t, arr.size(), TaskActionType.ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'deadline' is wrong! You do not have a '/by'!");
            System.out.println("The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS");
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Your time is not formatted correctly!");
            System.out.println("The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'");
            System.out.println("The correct format is: deadline 'description' /by 'YYYY-MM-DDTHH:MM:SS'");
        }
    }

    public static void handleEvent(String s, ArrayList<Task> arr) {
        try {
            int slashIndex = s.indexOf("/from");
            int slashIndexTwo = s.indexOf("/to", slashIndex + 1);
            Task t = new Event(s.substring(6, slashIndex - 1),
                    LocalDateTime.parse(s.substring(slashIndex + 6, slashIndexTwo - 1)),
                    LocalDateTime.parse(s.substring(slashIndexTwo + 4)));
            arr.add(t);
            taskPrint(t, arr.size(), TaskActionType.ADD);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR! Your formatting of your 'event' is wrong!");
            System.out.println("You need both a '/from' and a '/to'");
            System.out.println("The correct format is: event 'description' /from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'");
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Your time is not formatted correctly!");
            System.out.println("The correct format for a time is: 'YYYY-MM-DDTHH:MM:SS'");
            System.out.println("The correct format is: event 'description' /from YYYY-MM-DDTHH:MM:SS /to YYYY-MM-DDTHH:MM:SS'");
        }
    }

    public static void loadSavedTasks(String s, ArrayList<Task> arr) {
        String[] stringArray = s.split(",");
        switch (stringArray[0]) {
        case "T":
            arr.add(new Todo(stringArray[2], stringArray[1].equals("1")));
            break;
        case "D":
            arr.add(new Deadline(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3])));
            break;
        case "E":
            arr.add(new Event(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3]), LocalDateTime.parse(stringArray[4])));
        }
    }

    public static void handleDelete(String s, ArrayList<Task> arr) {
        try {
            int count = Integer.parseInt(s.substring(7)) - 1;
            Task t = arr.remove(count);
            taskPrint(t, arr.size(), TaskActionType.REMOVE);
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

    public static void main(String[] args) {
        ArrayList<Task> taskArr = new ArrayList<>(100);
        welcomeMessage();

        File savedTasks = new File("./data/benjamin.txt");
        if (!savedTasks.exists()) {
            try {
                new File("./data/").mkdirs();
                savedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println("Sorry, error getting file storage ! " + e.getMessage());
                return;
            }
        }

        try {
            Scanner reader = new Scanner(savedTasks);
            while (reader.hasNext()) {
                loadSavedTasks(reader.nextLine(), taskArr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, issue reading file " + e.getMessage());
            return;
        }

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
            } else if (s.startsWith("delete")) {
                handleDelete(s, taskArr);
            }

            else {
                System.out.println("Hey! I do not understand that. Please try something else!");
            }
            printDivider();
            s = sc.nextLine();
            printDivider();
        }

        try {
            FileWriter writer = new FileWriter(savedTasks);
            for (Task task : taskArr) {
                writer.write(task.saveAsString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save your tasks! " + e.getMessage());
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }
}
