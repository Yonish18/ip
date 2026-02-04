import java.util.Scanner;

public class Chotu {

    private static String divider = "\u001B[34m" + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static String[] items = new String[100];
    private static int itemQuantity = 0;
    private static boolean[] isDone = new boolean[100];

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
                        " ║  2. Add/List/Mark Items                                ║\n" +
                        " ║                                                        ║\n" +
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
                System.out.println(divider + "Enter the items you would like to add to your list.\n" +
                        "Type 'list' to list your added items.\n" +
                        "Type 'mark'/'unmark' [ITEM NUMBER] to mark items as done/not done.\n" +
                        "Type 'bye' to exit\n");
                String input = takeUserInput();
                if ((input.toLowerCase().startsWith("list"))) {
                    listItems();
                } else if(input.toLowerCase().startsWith("bye")) {
                    choice = "bye";
                } else if(input.toLowerCase().startsWith("mark")) {
                    markDone(Integer.parseInt(input.substring(5,6)) - 1);
                } else if(input.toLowerCase().startsWith("unmark")) {
                    markUndone(Integer.parseInt(input.substring(7,8)) - 1);
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
        items[itemQuantity] = item;
        itemQuantity += 1;
        System.out.println(divider + "added: " + item + "\n" + divider);
    }

    public static void listItems() {
        if (itemQuantity == 0) {2
            System.out.println(divider + " Your list is empty! Add some items first.\n" + divider);
            return;
        }

        System.out.print(divider);
        for (int i = 0; i < itemQuantity; i++) {
            if (isDone[i]) {
                System.out.println(" " + (i + 1) + ". " + "[X] " + items[i]);
            } else {
                System.out.println(" " + (i + 1) + ". " + "[ ] " + items[i]);
            }
            System.out.print(divider);
        }
    }

    public static boolean validChoice(String choice) {
        if((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.toLowerCase().equals("bye"))) {
            return true;
        }
        return false;
    }

    public static void markDone(int index) {
        isDone[index] = true;
        System.out.println(divider + "Nice! I've marked this task as done:\n" + " [X] " + items[index] + "\n" + divider);
    }

    public static void markUndone(int index) {
        isDone[index] = false;
        System.out.println(divider + "OK, I've marked this task as not done yet:\n" + " [ ] " + items[index] + "\n" + divider);
    }
}
