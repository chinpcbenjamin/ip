import java.util.Scanner;

public class BenjaminBot {
    public static void main(String[] args) {
        System.out.println("___________________________________________________________________");
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String s = "";
        while (!s.equals("bye")) {
            s = sc.next();
            System.out.println(s);
            System.out.println("___________________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________");
    }
}
