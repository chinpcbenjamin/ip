import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private File savedTasks;

    public Storage(String pathname) {
        this.savedTasks = new File(pathname);
        if (!savedTasks.exists()) {
            try {
                new File("./data/").mkdirs();
                savedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println("Sorry, error getting file storage ! " + e.getMessage());
            }
        }
    }

    public void load(TaskList taskArr) {
        try {
            Scanner reader = new Scanner(savedTasks);
            while (reader.hasNext()) {
                loadSavedTasks(reader.nextLine(), taskArr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, issue reading file " + e.getMessage());
        }
    }

    private void loadSavedTasks(String s, TaskList arr) {
        String[] stringArray = s.split(",");
        switch (stringArray[0]) {
        case "T":
            arr.addTask(new Todo(stringArray[2], stringArray[1].equals("1")));
            break;
        case "D":
            arr.addTask(new Deadline(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3])));
            break;
        case "E":
            arr.addTask(new Event(stringArray[2], stringArray[1].equals("1"), LocalDateTime.parse(stringArray[3]), LocalDateTime.parse(stringArray[4])));
        }
    }

    public void writeToStorage(TaskList taskArr) {
        try {
            FileWriter writer = new FileWriter(this.savedTasks);
            for (int i = 0; i < taskArr.getTaskCount(); i++) {
                writer.write(taskArr.getTask(i).saveAsString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save your tasks! " + e.getMessage());
        }
    }
}
