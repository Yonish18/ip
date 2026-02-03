import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String divider = "\u001B[34m" + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static ArrayList<String> items = new ArrayList<>();
    public static void main(String[] args) {
        String welcomeMsg = divider +
                " >> CHOTU ACTIVATED <<\n" +
                " Namaste! I'm Chotu\n" +
                " Your wish is my command. What can I do for you?\n";

        String menu =
                " ╔════════════════════════════════════════════════════════╗\n" +
                        " ║           CHOTU'S COMMAND MENU                         ║\n" +
                        " ╠════════════════════════════════════════════════════════╣\n" +
                        " ║  1. Echo Mode                                          ║\n" +
                        " ║  2. Add/List Items                                     ║\n" +
                        " ║  3. List Items                                         ║\n" +
                        " ║                                                        ║\n" +
                        " ║  Enter 'Bye' -> Exit                                   ║\n" +
                        " ╚════════════════════════════════════════════════════════╝\n" +
                        " Enter your choice (1-5): ";

        String exitMsg = divider +
                "Bye-Bye. Have a good day! Jai Shree Ram!\n" +
                divider;


        System.out.println(welcomeMsg);
        System.out.print(menu);
        String choice = takeUserInput();
        while (!(validChoice(choice))) {
            System.out.println(divider + "Please enter a valid choice!");
            choice = takeUserInput();
        }

        while (!(choice).toLowerCase().equals("bye")) {
            switch (choice) {
            case "1":
                echo();
                choice = "bye";
                break;

            case "2":
                System.out.println("Enter the items you would like to add to your list. Type 'list' to list your added items. Type 'bye' to exit\n");
                String input = takeUserInput();
                if ((input.toLowerCase().equals("list"))) {
                    listItems();
                } else if(input.toLowerCase().equals("bye")) {
                    choice = "bye";
                }
                else{
                    addItem(input);
                }
                break;

            }
        }
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

    public static void addItem(String item) {
        items.add(item);
        System.out.println(divider + "added: " + item + "\n" + divider);
    }

    public static void listItems() {
        if (items.isEmpty()) {
            System.out.println(divider + " Your list is empty! Add some items first.\n" + divider);
            return;
        }

        System.out.print(divider);
        for (int i = 0; i < items.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + items.get(i));
        }
        System.out.print(divider);
    }

    public static boolean validChoice(String choice) {
        if((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.toLowerCase().equals("bye"))) {
            return true;
        }
        return false;
    }
}
