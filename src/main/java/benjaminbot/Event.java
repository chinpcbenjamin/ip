package benjaminbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start and an end time. It corresponds to a task represented
 * by a String description, a boolean denoting whether the task has been completed, a
 * LocalDateTime of which the task starts, and another LocalDateTime of which the task ends.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a Deadline instance specified by the string description, a boolean indicating current status,
     * and start and end times.
     *
     * @param task A description of the task.
     * @param isDone A boolean describing whether the task has been completed or not.
     * @param startTime The time at which this task starts.
     * @param endTime The time at which this task ends.
     */
    public Event(String task, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(task, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a Deadline instance specified by the string description, and start and end times. This task is
     * not yet completed.
     *
     * @param task A description of the task.
     * @param startTime The time at which this task starts.
     * @param endTime The time at which this task ends.
     */
    public Event(String task, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.getCheckboxString()
                + super.getTask()
                + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"))
                + " to: "
                + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"))
                + ")";
    }

    @Override
    public String saveAsString() {
        return String.format(
                "E,%d,%s,%s,%s",
                super.getIsDone() ? 1 : 0,
                super.getTask(),
                this.startTime,
                this.endTime);
    }

    @Override
    public boolean isSameDate(LocalDate date) {
        boolean startsToday = this.startTime.getYear() == date.getYear()
                && this.startTime.getMonth() == date.getMonth()
                && this.startTime.getDayOfMonth() == date.getDayOfMonth();

        boolean endsToday = this.endTime.getYear() == date.getYear()
                && this.endTime.getMonth() == date.getMonth()
                && this.endTime.getDayOfMonth() == date.getDayOfMonth();
        return startsToday || endsToday;
    }
}
