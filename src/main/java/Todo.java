public class Todo extends Task {
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return super.getIsDone()
                ? "[T] [X] " + super.getTask()
                : "[T] [ ] " + super.getTask();
    }
}
