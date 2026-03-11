import java.util.ArrayList;

/**
 * Stores tasks and exposes task list operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a task by index.
     *
     * @param index zero-based task index
     * @return task at index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns a task by index.
     *
     * @param index zero-based task index
     * @return removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns current number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the list is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks a task as done.
     *
     * @param index zero-based task index
     */
    public void markDone(int index) {
        tasks.get(index).setDone(true);
    }

    /**
     * Marks a task as not done.
     *
     * @param index zero-based task index
     */
    public void markUndone(int index) {
        tasks.get(index).setDone(false);
    }

    /**
     * Parses and validates the task index in a command.
     *
     * @param input full command text
     * @param command command word
     * @return zero-based task index (after basic validation checks)
     */
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

    /**
     * Finds tasks containing a keyword in their descriptions.
     * Matching is case-insensitive.
     *
     * @param keyword normalized keyword
     * @return matching tasks (can be empty)
     */
    public ArrayList<Task> findByKeyword(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns the backing list used for storage.
     *
     * @return mutable task list used internaly for storage
     */
    public ArrayList<Task> asList() {
        return tasks;
    }
}
