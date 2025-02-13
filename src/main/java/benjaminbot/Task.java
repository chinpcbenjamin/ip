package benjaminbot;

/**
 * Represents activities that the user needs to do.
 */
public abstract class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructs a Task specified by a string, and a boolean indicating the completion status.
     *
     * @param s The description of the task.
     * @param b A boolean describing whether the task has been completed or not.
     */
    public Task(String s, boolean b) {
        this.task = s;
        this.isDone = b;
    }

    /**
     * Constructs a Task specified by a string. This task is not completed yet.
     *
     * @param s The description of the task.
     */
    public Task(String s) {
        this.task = s;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a boolean indicating whether this task's description contains the keyword being searched for.
     *
     * @param s The keyword to search for.
     * @return A boolean indicating whether this task's description contains the keyword being searched for.
     */
    public boolean containsKeyword(String s) {
        return this.task.contains(s);
    }

    @Override
    public String toString() {
        return this.getCheckboxString() + this.task;
    }

    /**
     * Formats the task for saving the task into the storage document.
     *
     * @return A string that is in the correct format for storage.
     */
    public abstract String saveAsString();

    public String getCheckboxString() {
        return this.isDone
                ? " [X] "
                : " [ ] ";
    }
}
