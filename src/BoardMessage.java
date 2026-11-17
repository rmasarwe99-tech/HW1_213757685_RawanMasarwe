import java.util.Date;

public class BoardMessage extends Message {
    private PriorityType priority;

    public BoardMessage(String sender, String content, Date sendDate, boolean isRead, PriorityType priority) {
        super(sender, content, sendDate, isRead);
        setPriority(priority);
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        this.priority = priority;
    }

    @Override
    public String generatePreview() {
        String preview = content.length() > 20 ? content.substring(0, 20) + "..." : content;
        return "[Board] " + preview;
    }

    @Override
    public String toString() {
        return super.toString() + ", Priority: " + priority;
    }
}