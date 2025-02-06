package benjaminbot;

/**
 * Represents a task that needs to be done. It has no start or end time associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a todo instance specified by the string description and a boolean indicating current status
     * @param task A description of the task.
     * @param isDone A boolean describing whether the task has been completed or not.
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     *  Constructs a todo instance specified by the string description. This task is not completed yet.
     * @param task A description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[T] [X] " + super.getTask()
                : "[T] [ ] " + super.getTask();
    }

    @Override
    public String saveAsString() {
        return String.format(
                "T,%d,%s",
                super.getIsDone() ? 1 : 0,
                super.getTask());
    }
}
