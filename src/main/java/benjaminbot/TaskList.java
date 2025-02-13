package benjaminbot;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskArr = new ArrayList<>(100);

    /**
     * Adds a new task to this task list.
     *
     * @param t Task to be added
     */
    public void addTask(Task t) {
        assert t != null : "Task should not be null";
        taskArr.add(t);
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param i Index of the task to be removed.
     * @return The task that is removed.
     */
    public Task removeTask(int i) {
        return taskArr.remove(i);
    }

    public Task getTask(int i) {
        return taskArr.get(i);
    }

    /**
     * Sets the status of the task at the specified index of the list to be 'done'.
     *
     * @param i Index of the task to set the status to 'done'.
     */
    public void markTask(int i) {
        Task t = taskArr.get(i);
        t.setDone();
    }

    /**
     * Sets the status of the task at the specified index of the list to be 'not done'.
     *
     * @param i Index of the task to set the status to 'not done'.
     */
    public void unmarkTask(int i) {
        Task t = taskArr.get(i);
        t.setNotDone();
    }

    public int getTaskCount() {
        return taskArr.size();
    }

    /**
     * Checks whether the tasks stored by this task list contains the keyword.
     *
     * @param s Keyword to be searched for
     * @return Task ArrayList containing all the tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksContainingKeyword(String s) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t : this.taskArr) {
            if (t.containsKeyword(s)) {
                list.add(t);
            }
        }
        return list;
    }
}
