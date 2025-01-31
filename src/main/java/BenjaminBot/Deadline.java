package BenjaminBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline to complete by. It corresponds to a task represented
 * by a String description, a boolean denoting whether the task has been completed, and a
 * LocalDateTime of which the task must be completed by.
 */
public class Deadline extends Task {
    private final LocalDateTime endTime;

    /**
     * Constructs a Deadline instance specified by the string description, a boolean indicating current status,
     * and an end time.
     * @param task A description of the task.
     * @param isDone A boolean describing whether the task has been completed or not.
     * @param endTime The deadline by which this task must be completed by.
     */
    public Deadline(String task, boolean isDone, LocalDateTime endTime) {
        super(task, isDone);
        this.endTime = endTime;
    }

    /**
     * Constructs a Deadline instance specified by the task, and an end time. This task is not yet completed.
     * @param task A description of the task.
     * @param endTime The deadline by which this task must be completed by.
     */
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