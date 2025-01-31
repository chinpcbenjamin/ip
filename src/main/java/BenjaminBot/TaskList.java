package BenjaminBot;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr = new ArrayList<>(100);

    public void addTask(Task t) {
        taskArr.add(t);
    }

    public Task removeTask(int i) {
        return taskArr.remove(i);
    }

    public Task getTask(int i) {
        return taskArr.get(i);
    }

    public void markTask(int i) {
        Task t = taskArr.get(i);
        t.setDone();
    }

    public void unmarkTask(int i) {
        Task t = taskArr.get(i);
        t.setNotDone();
    }

    public int getTaskCount() {
        return taskArr.size();
    }

    /**
     * Checks whether the tasks stored by this task list contains the keyword.
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
