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

    public ArrayList<Task> asList() {
        return tasks;
    }
}
