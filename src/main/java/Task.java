/**
 * Represents a task with a description and done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets this task as done or not done.
     *
     * @param done true if done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns whether this task is done.
     *
     * @return true if done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "[X]" if done, else "[ ]"
     */
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns this task in display format.
     *
     * @return formatted task text
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
