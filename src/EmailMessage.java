import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String sender, String content, Date sendDate, boolean isRead, String subject) {
        super(sender, content, sendDate, isRead);
        setSubject(subject);
        this.attachments = new ArrayList<>();
    }

    public String getSubject() {return subject;}

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty.");
        }
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {return attachments;}

    public void addAttachment(File file) throws AttachmentException {
        if (file == null) {
            throw new AttachmentException("Error: Cannot add a null file as an attachment.");
        }
        attachments.add(file);
    }

    public void removeAttachment(File file) throws AttachmentException {
        if (!attachments.contains(file)) {
            throw new AttachmentException("Error: File not found in attachments.");
        }
        attachments.remove(file);
    }

    @Override public String generatePreview() {
        return "[Email] Subject: " + subject + " | Content: " + (content.length() > 15 ? content.substring(0, 15) + "..." : content); }

    @Override public void printCommunicationMethod() {
        System.out.println("Communication Method: Digital Email Transmission.");}

    @Override public String toString() {
        return super.toString() + ", Subject: " + subject + ", Attachments Count: " + attachments.size();}
}