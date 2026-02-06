import java.util.Scanner;

public class Chotu {

    final private static String divider = "\u001B[34m" + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    private static String TaskMenuMsg = divider + "Enter the tasks you would like to add to your list." +
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

        switch (choice) {
            case "1":
                echo();
                break;

            case "2":
                taskManager();
                break;
            }
        System.out.println(exitMsg);
        }


    public static void taskManager() {
        System.out.println(TaskMenuMsg);
        String input = takeUserInput();
        while(!input.equalsIgnoreCase("bye")) {
            String inputLowercase = input.toLowerCase();
            if ((inputLowercase.startsWith("list"))) {
                listTasks();
            } else if (inputLowercase.startsWith("bye")) {
                break;
            } else if (inputLowercase.startsWith("mark")) {
                markDone(Integer.parseInt(input.substring(5).trim()) - 1);
            } else if (inputLowercase.startsWith("unmark")) {
                markUndone(Integer.parseInt(input.substring(7).trim()) - 1);
            } else if (inputLowercase.startsWith("todo")) {
                addTodo(input);
            } else if (inputLowercase.startsWith("deadline")) {
                addDeadline(input);
            } else if (inputLowercase.startsWith("event")) {
                addEvent(input);
            } else {
                System.out.println("Invalid input! Please try again.");
            }
            input = takeUserInput();
        }
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

    public static void addItem(Task task) {
        tasks[numTasks] = task;
        numTasks += 1;
        System.out.println(divider + "added: " + task + "\n" + divider);
    }

    public static void listTasks() {
        if (numTasks == 0) {
            System.out.println(divider + " Your list is empty! Add some tasks first.\n" + divider);
            return;
        }

        System.out.println(divider + " Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
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
        tasks[index].setDone(true);
        System.out.println(divider + "Nice! I've marked this task as done:\n" + " " + tasks[index] + "\n" + divider);
    }

    public static void markUndone(int index) {
        tasks[index].setDone(false);
        System.out.println(divider + "OK, I've marked this task as not done yet:\n" + " " + tasks[index] + "\n" + divider);
    }

    public static void createToDo(String todo) {

    }

    public static void addDeadline(String input) {
        int byIndex = input.toLowerCase().indexOf("/by");
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        Task task = new Deadline(description, by);
        tasks[numTasks] = task;
        numTasks++;
        printAddedTask(task);
    }

    public static void addTodo(String input) {
        String description = input.substring(4).trim();
        Task task = new Todo(description);
        tasks[numTasks] = task;
        numTasks++;
        printAddedTask(task);
    }

    public static void addEvent(String input) {
        String inputLower = input.toLowerCase();
        int fromIndex = inputLower.indexOf("/from");
        int toIndex = inputLower.indexOf("/to");
        String description = input.substring(5, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        Task task = new Event(description, from, to);
        tasks[numTasks] = task;
        numTasks++;
        printAddedTask(task);
    }

    private static void printAddedTask(Task task) {
        System.out.println(divider + " Got it. I've added this task:\n" +
                "  " + task + "\n" +
                " Now you have " + numTasks + " tasks in the list.\n" +
                divider);
    }
}

