package System_Design_Principles.Dependeny_Inversion_Principle;

// GmailClient class
class GmailClient1 {
    public void sendMail(String recipient, String subject, String body) {
        // Logic to send an email
        System.out.println("Email sent to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

// EmailService class
class EmailService1 {
    private GmailClient1 gmailClient;

    public EmailService1() {
        this.gmailClient = new GmailClient1();
    }

    public void sendEmail(String recipient, String subject, String body) {
        gmailClient.sendMail(recipient, subject, body);
    }
}

// Main class to test
public class BeforeDIP {
    public static void main(String[] args) {
        EmailService1 emailService = new EmailService1();
        emailService.sendEmail("test@example.com", "Hello", "This is a test email.");
    }
}
