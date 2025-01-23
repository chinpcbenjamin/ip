import java.util.ArrayList;
import java.util.Scanner;

public class BenjaminBot {
    public static void main(String[] args) {
        ArrayList<String> strArr = new ArrayList<>(100);
        System.out.println("___________________________________________________________________");
        System.out.println("Hello! I'm BenjaminBot");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("___________________________________________________________________");
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < strArr.size(); i++) {
                    System.out.println(i + 1 + ". " + strArr.get(i));
                }
            } else {
                strArr.add(s);
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
