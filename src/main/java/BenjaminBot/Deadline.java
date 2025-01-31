package BenjaminBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime endTime;

    public Deadline(String task, boolean isDone, LocalDateTime endTime) {
        super(task, isDone);
        this.endTime = endTime;
    }

    public Deadline(String task, LocalDateTime endTime) {
        super(task);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[D] [X] " + super.getTask() + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + ")"
                : "[D] [ ] " + super.getTask() + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")) + ")";
    }

    @Override
    public String saveAsString() {
        return String.format("D,%d,%s,%s", super.getIsDone() ? 1 : 0, super.getTask(), this.endTime);
    }
}