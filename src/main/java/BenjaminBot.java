import java.util.ArrayList;
import java.util.Scanner;

public class BenjaminBot {
    public static void main(String[] args) {
        ArrayList<Task> taskArr = new ArrayList<>(100);
        System.out.println("___________________________________________________________________");
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("___________________________________________________________________");

        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < taskArr.size(); i++) {
                    System.out.println(i + 1 + ". " + taskArr.get(i));
                }
            } else if (s.startsWith("mark")) {
                int count = Integer.parseInt(s.substring(5)) - 1;
                Task t = taskArr.get(count);
                t.setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t);
            } else if (s.startsWith("unmark")) {
                int count = Integer.parseInt(s.substring(7)) - 1;
                Task t = taskArr.get(count);
                t.setNotDone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  " + t);
            } else {
                Task t = new Task(s);
                taskArr.add(t);
                System.out.println("added: " + s);
            }
            System.out.println("___________________________________________________________________");
            s = sc.nextLine();
            System.out.println("___________________________________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________");
    }
}
