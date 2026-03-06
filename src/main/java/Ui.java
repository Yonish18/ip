import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles interactions with the user.
 */
public class Ui {
    public static final String DIVIDER = "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Reads one command line from the user.
     *
     * @return user input line
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints text to standard output.
     *
     * @param message text to print
     */
    public void show(String message) {
        System.out.print(message);
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.print(DIVIDER);
    }

    /**
     * Prints tasks that match a find command.
     *
     * @param matches matching tasks
     */
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
