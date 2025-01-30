import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String task, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(task, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String task, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[E] [X] " + super.getTask() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + ")"
                : "[E] [ ] " + super.getTask() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + ")";
    }

    @Override
    public String saveAsString() {
        return String.format("E,%d,%s,%s,%s", super.getIsDone() ? 1 : 0, super.getTask(), this.startTime, this.endTime);
    }
}
