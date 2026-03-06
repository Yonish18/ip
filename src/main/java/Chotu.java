public class Chotu {

    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage("data/Chotu.txt");
    private static final Parser parser = new Parser();
    private static TaskList tasks = new TaskList();
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

    private static String welcomeMsg = Ui.DIVIDER +
            " >> CHOTU ACTIVATED <<\n" +
            " Namaste! I'm Chotu\n" +
            " Your wish is my command. What can I do for you?\n";


    private static String exitMsg = Ui.DIVIDER +
            "Bye-Bye. Have a good day! Jai Shree Ram!\n" +
            Ui.DIVIDER;

    private static String buildTaskMenuMsg() {
        return Ui.DIVIDER
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

        storage.loadTasks(tasks.asList(), ui);
        ui.show(welcomeMsg);
        ui.show(menu);
        String choice = getValidChoice(takeUserInput());

        switch (choice) {
            case "1":
                echo();
                break;

            case "2":
                taskManager();
                break;
            }
        ui.show(exitMsg);
        //use this to test branching22
        }


    public static void taskManager() throws ChotuException {
        ui.show(TaskMenuMsg + "\n");
        String input = takeUserInput();
        while(!input.equalsIgnoreCase("bye")) {
            String commandWord = parser.getCommandWord(input);
            try {
                if (commandWord.equals("list")) {
                    listTasks();
                } else if (commandWord.equals("bye")) {
                    break;
                } else if (commandWord.equals("mark")) {
                    markDone(tasks.parseTaskIndex(input, "mark"));
                } else if (commandWord.equals("unmark")) {
                    markUndone(tasks.parseTaskIndex(input, "unmark"));
                } else if (commandWord.equals("todo")) {
                    addTodo(input);
                } else if (commandWord.equals("deadline")) {
                    addDeadline(input);
                } else if (commandWord.equals("event")) {
                    addEvent(input);
                } else if (commandWord.equals("delete")) {
                    deleteTask(tasks.parseTaskIndex(input, "delete"));
                } else {
                    throw new ChotuException("Kind sir, please enlighten me what that means. I don't understand that command.");
                }
            } catch (ChotuException e) {
                String message = e.getMessage();
                if (message == null || message.trim().isEmpty()) {
                    message = "Sir, kindly enter a VALID input >:( ";
                }
                ui.show(Ui.DIVIDER + message + "\n" + Ui.DIVIDER);
            }
            input = takeUserInput();
        }
    }

    public static String getValidChoice(String choice) {
        while (!(isValidChoice(choice))) {
            ui.show(Ui.DIVIDER + "Please enter a valid choice!\n");
            choice = takeUserInput();
        }
        return choice;
    }

    public static void echo() {
        ui.show(Ui.DIVIDER + "Welcome to Echo mode. I'll repeat what you say!\n");
        String userInput = takeUserInput();
        while (!(userInput.equalsIgnoreCase("bye"))) {
            ui.show(Ui.DIVIDER + userInput + "\n" + Ui.DIVIDER);
            userInput = takeUserInput();
        }
    }

    public static String takeUserInput() {
        return ui.readCommand();
    }

    public static void addItem(Task task) {
        tasks.add(task);
        ui.show(Ui.DIVIDER + "added: " + task + "\n" + Ui.DIVIDER);
        storage.saveTasks(tasks.asList(), ui);
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            ui.show(Ui.DIVIDER + " Your list is empty, congrats! You have no work. Add some tasks if you want.\n" + Ui.DIVIDER);
            return;
        }

        ui.show(Ui.DIVIDER + " Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show(" " + (i + 1) + "." + tasks.get(i) + "\n");
        }
        ui.show(Ui.DIVIDER);
    }

    public static boolean isValidChoice(String choice) {
        //This method checks whether user's choice is valid or not
        if((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.equalsIgnoreCase("bye"))) {
            return true;
        }
        return false;
    }

    public static void markDone(int index) {
        tasks.markDone(index);
        ui.show(Ui.DIVIDER + "Nice! I've marked this task as done:\n" + " " + tasks.get(index) + "\n" + Ui.DIVIDER);
        storage.saveTasks(tasks.asList(), ui);
    }

    public static void markUndone(int index) {
        tasks.markUndone(index);
        ui.show(Ui.DIVIDER + "OK, I've marked this task as not done yet:\n" + " " + tasks.get(index) + "\n" + Ui.DIVIDER);
        storage.saveTasks(tasks.asList(), ui);
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
        storage.saveTasks(tasks.asList(), ui);
    }

    public static void addTodo(String input) {
        String description = input.substring("todo".length()).trim(); //avoiding magic literals here
        if (description.isEmpty()) {
            throw new ChotuException("Well, sir, I can't add an empty todo now, can I? :/");
        }
        Task task = new Todo(description);
        tasks.add(task);
        printAddedTask(task);
        storage.saveTasks(tasks.asList(), ui);
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
        storage.saveTasks(tasks.asList(), ui);
    }

    private static void printAddedTask(Task task) {
        ui.show(Ui.DIVIDER + " Got it. I've added this task:\n" +
                "  " + task + "\n" +
                " Now you have " + tasks.size() + " tasks in the list.\n" +
                Ui.DIVIDER);
    }

    public static void deleteTask(int index) {
        Task task = tasks.remove(index);
        storage.saveTasks(tasks.asList(), ui);
        System.out.println("OK, I have deleted *" + task + "* from your list");
        listTasks();
    }
}
