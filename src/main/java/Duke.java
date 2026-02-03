import java.util.Scanner;

public class Duke {

    private static String divider = "\u001B[34m" + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    public static void main(String[] args) {
        String welcomeMsg = divider +
                " >> CHOTU ACTIVATED <<\n" +
                " Namaste! I'm Chotu\n" +
                " Your wish is my command. What can I do for you?" ;
        String exitMsg =                 divider +
                "Bye-Bye. Have a good day! Jai Shree Ram!\n" +
                divider ;


        System.out.println(welcomeMsg);
        echo();
        System.out.println(exitMsg);
    }

    public static void echo() {
        String userInput = takeUserInput();
        while (!(userInput.toLowerCase().equals("bye"))) {
            System.out.println(divider + userInput + "\n" + divider);
            userInput = takeUserInput();
        }
    }

    public static String takeUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
