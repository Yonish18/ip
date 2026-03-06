import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Chotu {

    private static final String DIVIDER = "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("Chotu.txt");
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
                + "TASK MANAGER MENU\n\n"
                + "Type 'list' to list your added tasks.\n"
                + "Type 'mark'/'unmark' [TASK NUMBER] to mark tasks as done/not done.\n"
                + "Type 'todo [DESCRIPTION]' to add a todo task."
                + "Type 'event [DESCRIPTION] /from [START_DETAILS] /to [END_DETAILS]' to add an event.\n"
                + "Type 'deadline [DESCRIPTION] /by [DEADLINE]' to add a deadline.\n"
                + "Type 'delete [TASK NUMBER]' to delete a task from your list"
                + "Type 'bye' to exit";
    }


    public static void main(String[] args) {

        loadTasksFromDisk();
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
        //use this to test branching22
        }


    public static void taskManager() throws ChotuException {
        System.out.println(TaskMenuMsg);
        String input = takeUserInput();
        while(!input.equalsIgnoreCase("bye")) {
            String inputLowercase = input.toLowerCase().trim();
            try {
                if ((inputLowercase.startsWith("list"))) {
                    listTasks();
                } else if (inputLowercase.startsWith("bye")) {
                    break;
                } else if (inputLowercase.startsWith("mark")) {
                    markDone(parseTaskIndex(input, "mark"));
                } else if (inputLowercase.startsWith("unmark")) {
                    markUndone(parseTaskIndex(input, "unmark"));
                } else if (inputLowercase.startsWith("todo")) {
                    addTodo(input);
                } else if (inputLowercase.startsWith("deadline")) {
                    addDeadline(input);
                } else if (inputLowercase.startsWith("event")) {
                    addEvent(input);
                } else if (inputLowercase.startsWith("delete")) {
                    deleteTask(parseTaskIndex(input, "delete"));
                } else {
                    throw new ChotuException("Kind sir, please enlighten me what that means. I don't understand that command.");
                }
            } catch (ChotuException e) {
                String message = e.getMessage();
                if (message == null || message.trim().isEmpty()) {
                    message = "Sir, kindly enter a VALID input >:( ";
                }
                System.out.println(DIVIDER + message + "\n" + DIVIDER);
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
        tasks.add(task);
        System.out.println(DIVIDER + "added: " + task + "\n" + DIVIDER);
        saveTasksToDisk();
    }

    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println(DIVIDER + " Your list is empty, congrats! You have no work. Add some tasks if you want.\n" + DIVIDER);
            return;
        }

        System.out.println(DIVIDER + " Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
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
        tasks.get(index).setDone(true);
        System.out.println(DIVIDER + "Nice! I've marked this task as done:\n" + " " + tasks.get(index) + "\n" + DIVIDER);
        saveTasksToDisk();
    }

    public static void markUndone(int index) {
        tasks.get(index).setDone(false);
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n" + " " + tasks.get(index) + "\n" + DIVIDER);
        saveTasksToDisk();
    }

    public static void addDeadline(String input) {
        String rest = input.substring("deadline".length()).trim();
        if (rest.isEmpty()) {
            throw new ChotuException("Kind sir, please enlighten me how to add a deadline when you didn't even provide a description.");
        }
        int byIndex = rest.toLowerCase().indexOf("/by");
        if (byIndex == -1) {
            throw new ChotuException("Sir, tonight or a decade later? Please specify by what time this needs to be done.");
        }
        String description = rest.substring(0, byIndex).trim();
        String by = rest.substring(byIndex + 3).trim();

        if (description.isEmpty()) {
            throw new ChotuException("Kind sir, please enlighten me how to add a deadline when you didn't even provide a description.");
        } else if (by.isEmpty()) {
            throw new ChotuException("Sir, tonight or a decade later? Please specify by what time this needs to be done.");
        }

        Task task = new Deadline(description, by);
        tasks.add(task);
        printAddedTask(task);
        saveTasksToDisk();
    }

    public static void addTodo(String input) {
        String description = input.substring("todo".length()).trim(); //avoiding magic literals here
        if (description.isEmpty()) {
            throw new ChotuException("Well, sir, I can't add an empty todo now, can I? :/");
        }
        Task task = new Todo(description);
        tasks.add(task);
        printAddedTask(task);
        saveTasksToDisk();
    }

    public static void addEvent(String input) {
        String rest = input.substring("event".length()).trim();
        if (rest.isEmpty()) {
            throw new ChotuException("Sir, are you going for dinner or pilates class? I can't add an event without a description!");
        }
        String restLower = rest.toLowerCase();
        int fromIndex = restLower.indexOf("/from");
        int toIndex = restLower.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new ChotuException("I cannot add this event if you don't tell me when it starts and ends.");
        }
        String description = rest.substring(0, fromIndex).trim();
        String from = rest.substring(fromIndex + 5, toIndex).trim();
        String to = rest.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChotuException("Sir, what is the event? I can't add it without a description.");
        } else if (from.isEmpty()) {
            throw new ChotuException("I cannot add this event if you don't tell me when it starts.");
        } else if (to.isEmpty()) {
            throw new ChotuException("I cannot add this event if you don't tell me when it ends, unless you plan to stay forever.");
        }
        Task task = new Event(description, from, to);
        tasks.add(task);
        printAddedTask(task);
        saveTasksToDisk();
    }

    private static void printAddedTask(Task task) {
        System.out.println(DIVIDER + " Got it. I've added this task:\n" +
                "  " + task + "\n" +
                " Now you have " + tasks.size() + " tasks in the list.\n" +
                DIVIDER);
    }

    private static int parseTaskIndex(String input, String command) {
        /**
         * Parses a task index from user input.
         * Strips the command word, converts the remaining number to a 0-based index.
         */
        String remainder = input.substring(command.length()).trim();
        if (remainder.isEmpty()) {
            throw new ChotuException("Sir, please include the task number, like \"" + command + " 2\".");
        }
        try {
            int index = Integer.parseInt(remainder) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new ChotuException("Sir, that task number doesn't exist. Must be a ghost task. You have " + tasks.size() + " tasks.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new ChotuException("Sir, that task number is not valid. Try a number like 1, 2, 3.");
        }
    }

    public static void deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("OK, I have deleted *" + task + "* from your list");
        listTasks();
    }

    private static void loadTasksFromDisk() {
        if (!Files.exists(DATA_FILE)) {
            return;
        }
        int skipped = 0;
        try {
            List<String> lines = Files.readAllLines(DATA_FILE);
            for (String line : lines) {
                if (line == null) {
                    continue;
                }
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] parts = trimmed.split("\\s*\\|\\s*");
                if (parts.length < 3) {
                    skipped++;
                    continue;
                }
                String type = parts[0];
                String doneFlag = parts[1];
                String description = parts[2];
                if (description.isEmpty()) {
                    skipped++;
                    continue;
                }

                Task task;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    if (parts.length < 4) {
                        skipped++;
                        continue;
                    }
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E")) {
                    if (parts.length < 5) {
                        skipped++;
                        continue;
                    }
                    task = new Event(description, parts[3], parts[4]);
                } else {
                    skipped++;
                    continue;
                }

                if (doneFlag.equals("1")) {
                    task.setDone(true);
                } else if (doneFlag.equals("0")) {
                    task.setDone(false);
                } else {
                    skipped++;
                    continue;
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(DIVIDER + "Warning: I couldn't read saved tasks.\n" + DIVIDER);
            return;
        }

        if (skipped > 0) {
            System.out.println(DIVIDER + "Warning: I skipped " + skipped + " corrupted task line(s) in saved data.\n" + DIVIDER);
        }
    }

    
    private static void saveTasksToDisk() {
        try {
            Files.createDirectories(DATA_DIR);
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                content.append(serializeTask(task));
                if (i < tasks.size() - 1) {
                    content.append(System.lineSeparator());
                }
            }
            Files.write(DATA_FILE, content.toString().getBytes());
        } catch (IOException e) {
            System.out.println(DIVIDER + "Warning: I couldn't save your tasks.\n" + DIVIDER);
        }
    }

    private static String serializeTask(Task task) {
        String doneFlag = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + doneFlag + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneFlag + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneFlag + " | " + event.getDescription() + " | " + event.from + " | " + event.to;
        }
        return "T | " + doneFlag + " | " + task.getDescription();
    }
}
