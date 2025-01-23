public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String task, boolean isDone, String startTime, String endTime) {
        super(task, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[E] [X] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")"
                : "[E] [ ] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
