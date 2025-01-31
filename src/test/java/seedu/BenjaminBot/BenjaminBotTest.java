package seedu.BenjaminBot;

import BenjaminBot.Deadline;
import BenjaminBot.Event;
import BenjaminBot.TaskList;
import BenjaminBot.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenjaminBotTest {
    @Test
    public void TaskListTest() {
        // tests TaskList's getTaskCount(), addTask(Task t), and removeTask(int i);
        TaskList testArr = new TaskList();
        assertEquals(0, testArr.getTaskCount());

        testArr.addTask(new Todo("todo 1"));
        assertEquals(1, testArr.getTaskCount());

        testArr.addTask(new Deadline("deadline 1", LocalDateTime.parse("2025-01-31T08:00:00")));
        assertEquals(2, testArr.getTaskCount());

        testArr.addTask(new Event(
                "event 1",
                LocalDateTime.parse("2025-01-31T08:00:00"),
                LocalDateTime.parse("2025-01-31T09:00:00")));
        assertEquals(3, testArr.getTaskCount());

        testArr.removeTask(0);
        assertEquals(2, testArr.getTaskCount());
    }
}
