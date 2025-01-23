public class Deadline extends Task {
    private final String endTime;

    public Deadline(String task, boolean isDone, String endTime) {
        super(task, isDone);
        this.endTime = endTime;
    }

    public Deadline(String task, String endTime) {
        super(task);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[D] [X] " + super.getTask() + " (by: " + this.endTime + ")"
                : "[D] [ ] " + super.getTask() + " (by: " + this.endTime + ")";
    }
}