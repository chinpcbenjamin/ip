package BenjaminBot;

public abstract class Task {
    private final String task;
    private boolean isDone;

    public Task(String s, boolean b) {
        this.task = s;
        this.isDone = b;
    }

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

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.task
                : "[ ] " + this.task;
    }

    public abstract String saveAsString();
}
