package benjaminbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Represents the file that the user saves the lists of tasks to upon closing of the BenjaminBot.
 */
public class Storage {
    private File savedTasks;

    /**
     * Loads the file that contains the saved lists of tasks, specified by the pathname. If the file
     * at the specified pathname does not exist, then a new empty file is created.
     *
     * @param pathname The pathname to which the lists of tasks is saved at.
     */
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

    /**
     * Loads the list of saved tasks from the file specified by this Storage instance, and adds the saved tasks
     * to the TaskList instance used by a BenjaminBot instance.
     *
     * @param taskArr The TaskList instance to which the saved tasks will be loaded to.
     */
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

    /**
     * Decides, based on the current entry retrieved from the file specified by this Storage instance, the type of
     * task that it is, and accordingly adds that task to the TaskList instance
     * @param s A string representing one task, that was retrieved from the list of saved tasks from this
     *          Storage instance.
     * @param arr The TaskList to which the saved task will be loaded to.
     */
    private void loadSavedTasks(String s, TaskList arr) {
        String[] stringArray = s.split(",");
        switch (stringArray[0]) {
        case "T":
            arr.addTask(new Todo(
                    stringArray[2],
                    stringArray[1].equals("1")));
            break;
        case "D":
            arr.addTask(new Deadline(
                    stringArray[2],
                    stringArray[1].equals("1"),
                    LocalDateTime.parse(stringArray[3])));
            break;
        case "E":
            arr.addTask(new Event(stringArray[2],
                    stringArray[1].equals("1"),
                    LocalDateTime.parse(stringArray[3]),
                    LocalDateTime.parse(stringArray[4])));
            break;
        default:
            break;
        }
    }

    /**
     * Retrieves all the tasks from the provided TaskList instance, and stores them inside the file specified by this
     * Storage instance.
     * @param taskArr The TaskList from which there are tasks to be stored to memory.
     */
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
