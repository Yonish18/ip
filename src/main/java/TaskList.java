import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void markDone(int index) {
        tasks.get(index).setDone(true);
    }

    public void markUndone(int index) {
        tasks.get(index).setDone(false);
    }

    public int parseTaskIndex(String input, String command) {
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

    public ArrayList<Task> findByKeyword(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    public ArrayList<Task> asList() {
        return tasks;
    }
}
