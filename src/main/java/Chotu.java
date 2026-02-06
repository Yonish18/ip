import java.util.Scanner;

public class Chotu {

    final private static String divider = "\u001B[34m" + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    public static boolean[] isDone = new boolean[100];
    private static String itemsMenuMsg = divider + "Enter the tasks you would like to add to your list." +
            "Type 'list' to list your added tasks." +
        "Type 'mark'/'unmark' [ITEM NUMBER] to mark tasks as done/not done." +
        "Type 'bye' to exit";
    private static String menu =
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

    private static String welcomeMsg = divider +
            " >> CHOTU ACTIVATED <<\n" +
            " Namaste! I'm Chotu\n" +
            " Your wish is my command. What can I do for you?\n";


    private static String exitMsg = divider +
            "Bye-Bye. Have a good day! Jai Shree Ram!\n" +
            divider;


    public static void main(String[] args) {

        System.out.println(welcomeMsg);
        System.out.print(menu);
        String choice = getValidChoice(takeUserInput());

        while (!(choice).toLowerCase().equals("bye")) {
            switch (choice) {
            case "1":
                echo();
                choice = "bye";
                break;
            """
                    
            case "2":

                System.out.println(divider + "Enter the tasks you would like to add to your list.\n" +
                        "Type 'list' to list your added tasks.\n" +
                        "Type 'mark'/'unmark' [ITEM NUMBER] to mark tasks as done/not done.\n" +
                        "Type 'bye' to exit\n");
                String input = takeUserInput().toLowerCase();
                if ((input.startsWith("list"))) {
                    listItems();
                } else if(input.startsWith("bye")) {
                    choice = "bye";
                } else if(input.startsWith("mark")) {
                    markDone(Integer.parseInt(input.substring(5,6)) - 1);
                } else if(input.startsWith("unmark")) {
                    markUndone(Integer.parseInt(input.substring(7,8)) - 1);
                }
                else{
                    addItem(input);
                }
                break;
                """

            case "2":
                System.out.println(itemsMenuMsg);
                String input = takeUserInput();
                String inputLowercase = input.toLowerCase();
                if ((inputLowercase.startsWith("list"))) {
                    listTasks();
                } else if(inputLowercase.startsWith("bye")) {
                    choice = "bye";
                } else if(inputLowercase.startsWith("mark")) {
                    markDone(Integer.parseInt(input.substring(5,6)) - 1);
                } else if(inputLowercase.startsWith("unmark")) {
                    markUndone(Integer.parseInt(input.substring(7,8)) - 1);
                } else if(inputLowercase.startsWith("todo")) {
                    addTodo(input);
                } else if(inputLowercase.startsWith("deadline")) {
                    addDeadline(input);
                } else if(inputLowercase.startsWith("event")) {
                    addEvent(input);
                } else {
                    System.out.println("Invalid input! Please try again.");
                }

            }
        }
        System.out.println(exitMsg);
    }

    public static String getValidChoice(String choice) {
        while (!(isValidChoice(choice))) {
            System.out.println(divider + "Please enter a valid choice!");
            choice = takeUserInput();
        }
        return choice;
    }

    public static void echo() {
        System.out.print(divider + "Welcome to Echo mode. I'll repeat what you say!\n");
        String userInput = takeUserInput();
        while (!(userInput.equalsIgnoreCase("bye"))) {
            System.out.println(divider + userInput + "\n" + divider);
            userInput = takeUserInput();
        }
    }

    public static String takeUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void addItem(String item) {
        tasks[numTasks] = item;
        numTasks += 1;
        System.out.println(divider + "added: " + item + "\n" + divider);
    }

    public static void listTasks() {
        if (numTasks == 0) {
            System.out.println(divider + " Your list is empty! Add some tasks first.\n" + divider);
            return;
        }

        System.out.print(divider);
        for (int i = 0; i < numTasks; i++) {
            if (isDone[i]) {
                System.out.println(" " + (i + 1) + ". " + "[X] " + tasks[i]);
            } else {
                System.out.println(" " + (i + 1) + ". " + "[ ] " + tasks[i]);
            }

        }
        System.out.print(divider);
    }

    public static boolean isValidChoice(String choice) {
        if((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.equalsIgnoreCase("bye"))) {
            return true;
        }
        return false;
    }

    public static void markDone(int index) {
        isDone[index] = true;
        System.out.println(divider + "Nice! I've marked this task as done:\n" + " [X] " + tasks[index] + "\n" + divider);
    }

    public static void markUndone(int index) {
        isDone[index] = false;
        System.out.println(divider + "OK, I've marked this task as not done yet:\n" + " [ ] " + tasks[index] + "\n" + divider);
    }

    public static void createToDo(String todo) {

    }

    public static void addDeadline(String input) {
        String description = input.substring(9,input.indexOf('/'));
        String by = input.substring(input.indexOf('/')+1);
        tasks[numTasks] = new Deadline(description, by);
        numTasks++;
    }

    public static void addTodo(String input) {
        String description = input.substring(4);
        tasks[numTasks] = new Todo(description);
        numTasks++;
    }

    public static void addEvent(String input) {
        String description = input.substring(5, input.indexOf("/"));
        String from = input.substring(input.indexOf("/")+6, input.lastIndexOf("/"));
        String to = input.substring(input.lastIndexOf("/")+4);
        tasks[numTasks] = new Event(description, from, to);
        numTasks++;
    }
}


