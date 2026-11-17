import java.util.Date;

public abstract class Message {
	protected String sender;
	protected String content;
	protected Date sendDate;
	protected boolean isRead;
	
	public void Sender(String sender) {
		if (sender == null || sender.trim().isEmpty()) {
	        throw new IllegalArgumentException("Sender cannot be empty.");
	    }
	    this.sender = sender;
	}
	
	public void Content(String content) {
		if (content == null || content.trim().isEmpty()) {
	        throw new IllegalArgumentException("Content cannot be empty.");
	    }
	    this.content = content;
	}
	
	public void SendDate(Date sendDate) {this.sendDate = sendDate;}
	
	public void IsRead(boolean isRead) {this.isRead = isRead;}
	
	public String getSender() {return sender;}

	public String getContent() {return content;}

	public Date getSendDate() {return sendDate;}

	public boolean isRead() {return isRead;}
	
	public Message(String sender, String content, Date sendDate, boolean isRead) {
		Sender(sender);
		Content(content);
		SendDate(sendDate);
		IsRead(isRead);}
	
	public abstract String generatePreview();
	
	@Override public String toString() {
		return "Sender: " + sender + ", Content: " + content + ", Date: " + sendDate + ", Read: " + isRead;}
}
