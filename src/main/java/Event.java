public class Event extends Deadline {
    protected String description;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String checkBox;
        if(isDone[this.taskNum]) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return ("[E]" + checkBox + " " + this.getDescription() + " (from: " + this.from + " to: " + this.to + ")");
    }
}
