public class Task {
    private final int id;
    private final String text;
    private boolean complete;

    public Task(int id, String text) {
        this.id = id;
        this.text = text;
        complete = false;
    }

    public int getId() {
        return id;
    }

    public void completeTask() {
        complete = true;
    }

    public String toWrite() {
        return String.format("%s %d %d", text, id, complete ? 1 : 0);
    }

    public String toString() {
        return String.format("%s (id:%d)", text, id);
    }
}
