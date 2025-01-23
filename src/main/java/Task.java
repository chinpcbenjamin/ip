public class Task {
    private String task;
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

    @Override
    public String toString() {
        return isDone
                ? "[X] " + this.task
                : "[ ] " + this.task;
    }
}
