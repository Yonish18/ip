import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public static final String DIVIDER = "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public void show(String message) {
        System.out.print(message);
    }

    public void showLine() {
        System.out.print(DIVIDER);
    }

    public void showMatchingTasks(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            show(DIVIDER + "I couldn't find matching tasks.\n" + DIVIDER);
            return;
        }

        show(DIVIDER + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            show(" " + (i + 1) + "." + matches.get(i) + "\n");
        }
        show(DIVIDER);
    }
}
