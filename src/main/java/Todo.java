public class Todo extends Task {
    protected boolean isDone;
    public Todo(String description) {
        super(description);
        isDone = false;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String checkBox;
        if (isDone) {
            checkBox = "[X]";
        } else {
            checkBox= "[ ]";
        }
        return "[T]" + checkBox + " " + this.getDescription();
    }
}