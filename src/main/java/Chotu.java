import java.util.Scanner;

public class Chotu {

    private static final String DIVIDER = "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    private static String TaskMenuMsg = buildTaskMenuMsg();
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
                    " Enter your choice (1-2): ";

    private static String welcomeMsg = DIVIDER +
            " >> CHOTU ACTIVATED <<\n" +
            " Namaste! I'm Chotu\n" +
            " Your wish is my command. What can I do for you?\n";


    private static String exitMsg = DIVIDER +
            "Bye-Bye. Have a good day! Jai Shree Ram!\n" +
            DIVIDER;

    private static String buildTaskMenuMsg() {
        return DIVIDER
                + "Enter the tasks you would like to add to your list.\n"
                + "Type 'list' to list your added tasks.\n"
                + "Type 'mark'/'unmark' [ITEM NUMBER] to mark tasks as done/not done.\n"
                + "Type 'bye' to exit";
    }


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


    public static void taskManager() throws InvalidInputException {
        System.out.println(TaskMenuMsg);
        String input = takeUserInput();
        while(!input.equalsIgnoreCase("bye")) {
            String inputLowercase = input.toLowerCase();
            try {
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
            } catch (InvalidInputException e) {
                System.out.println("Please try again");
            }
            input = takeUserInput();
        }
    }

    public static String getValidChoice(String choice) {
        while (!(isValidChoice(choice))) {
            System.out.println(DIVIDER + "Please enter a valid choice!");
            choice = takeUserInput();
        }
        return choice;
    }

    public static void echo() {
        System.out.print(DIVIDER + "Welcome to Echo mode. I'll repeat what you say!\n");
        String userInput = takeUserInput();
        while (!(userInput.equalsIgnoreCase("bye"))) {
            System.out.println(DIVIDER + userInput + "\n" + DIVIDER);
            userInput = takeUserInput();
        }
    }

    public static String takeUserInput() {
        return SCANNER.nextLine();
    }

    public static void addItem(Task task) {
        tasks[numTasks] = task;
        numTasks += 1;
        System.out.println(DIVIDER + "added: " + task + "\n" + DIVIDER);
    }

    public static void listTasks() {
        if (numTasks == 0) {
            System.out.println(DIVIDER + " Your list is empty! Add some tasks first.\n" + DIVIDER);
            return;
        }

        System.out.println(DIVIDER + " Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.print(DIVIDER);
    }

    public static boolean isValidChoice(String choice) {
        //This method checks whether user's choice is valid or not
        if((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.equalsIgnoreCase("bye"))) {
            return true;
        }
        return false;
    }

    public static void markDone(int index) {
        try {
            tasks[index].setDone(true);
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n" + " " + tasks[index] + "\n" + DIVIDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVIDER + "This task number does not exist, Sir");
            throw new InvalidInputException();
        }
    }

    public static void markUndone(int index) {
        tasks[index].setDone(false);
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n" + " " + tasks[index] + "\n" + DIVIDER);
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
        System.out.println(DIVIDER + " Got it. I've added this task:\n" +
                "  " + task + "\n" +
                " Now you have " + numTasks + " tasks in the list.\n" +
                DIVIDER);
    }
}

