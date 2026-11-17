import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        messages.add(new BoardMessage("Admin", "Welcome students to the new semester!", new Date(), false, PriorityType.URGENT));
        messages.add(new BoardMessage("Manager", "Weekly meeting scheduled for tomorrow.", new Date(), true, PriorityType.REGULAR));

        File f1 = new File("Report", "pdf");
        EmailMessage email1 = new EmailMessage("Rawan", "Please review the attached project files.", new Date(), false, "Project Assignment");
        try {
            email1.addAttachment(f1);
        } catch (AttachmentException e) {
            e.printStackTrace();
        }
        messages.add(email1);
        messages.add(new EmailMessage("Ali", "Here is the link for our online lecture.", new Date(), true, "Lecture Link"));

        messages.add(new SMSMessage("System", "Your verification code is 4821.", new Date(), false, "0501112233"));
        messages.add(new SMSMessage("Store", "Your order has been shipped successfully.", new Date(), true, "0524445566"));

        int choice;
        do {
            System.out.println("\n--- MESSAGES MANAGEMENT MENU ---");
            System.out.println("1. Add Message");
            System.out.println("2. Delete Message");
            System.out.println("3. Print All Messages");
            System.out.println("4. Count Messages Containing Word");
            System.out.println("5. Print Digital Messages Only");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Choose type: 1. Board  2. Email  3. SMS");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter sender: ");
                    String sender = scanner.nextLine();
                    System.out.print("Enter content: ");
                    String content = scanner.nextLine();

                    if (type == 1) {
                        messages.add(new BoardMessage(sender, content, new Date(), false, PriorityType.REGULAR));
                        System.out.println("Board message added successfully!");
                    } else if (type == 2) {
                        System.out.print("Enter subject: ");
                        String subject = scanner.nextLine();
                        messages.add(new EmailMessage(sender, content, new Date(), false, subject));
                        System.out.println("Email message added successfully!");
                    } else if (type == 3) {
                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();
                        messages.add(new SMSMessage(sender, content, new Date(), false, phone));
                        System.out.println("SMS message added successfully!");
                    }
                    break;

                case 2:
                    if (messages.isEmpty()) {
                        System.out.println("No messages to delete.");
                        break;
                    }
                    System.out.println("Select index to delete (0 to " + (messages.size() - 1) + "):");
                    for (int i = 0; i < messages.size(); i++) {
                        System.out.println(i + ": " + messages.get(i).generatePreview());
                    }
                    int index = scanner.nextInt();
                    if (index >= 0 && index < messages.size()) {
                        messages.remove(index);
                        System.out.println("Message deleted successfully.");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- ALL MESSAGES ---");
                    for (Message m : messages) {
                        System.out.println(m.toString() + " | Preview: " + m.generatePreview());
                    }
                    break;

                case 4:
                    System.out.print("Enter word to search in content: ");
                    String word = scanner.nextLine();
                    int count = 0;
                    for (Message m : messages) {
                        if (m.getContent() != null && m.getContent().contains(word)) {
                            count++;
                        }
                    }
                    System.out.println("Found " + count + " messages containing the word: " + word);
                    break;

                case 5:
                    System.out.println("\n--- DIGITAL MESSAGES ONLY ---");
                    for (Message m : messages) {
                        if (m instanceof IDigital) {
                            System.out.println(m.toString());
                            ((IDigital) m).printCommunicationMethod();
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}