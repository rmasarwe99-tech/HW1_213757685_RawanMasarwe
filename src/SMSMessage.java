import java.util.Date;

public class SMSMessage extends Message {
    private String phoneNumber;

    public SMSMessage(String sender, String content, Date sendDate, boolean isRead, String phoneNumber) {
        super(sender, content, sendDate, isRead);
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override public String generatePreview() {
        return "[SMS to " + phoneNumber + "] " + (content.length() > 15 ? content.substring(0, 15) + "..." : content);}

    @Override public String toString() {
        return super.toString() + ", Phone Number: " + phoneNumber;}
}