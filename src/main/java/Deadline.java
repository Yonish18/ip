public class Deadline extends Todo {
    protected String by;
    protected int taskNum;  //INDEX IN TASKS
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description) {
        super(description);
    }

    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String checkBox;
        if(isDone[this.taskNum]) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return "[D]" + checkBox + " " + this.getDescription() + " (by: " + this.by + ")";
    }
}