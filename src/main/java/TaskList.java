import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void writeToStorage(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskArr) {
                writer.write(task.saveAsString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save your tasks! " + e.getMessage());
        }
    }
}
