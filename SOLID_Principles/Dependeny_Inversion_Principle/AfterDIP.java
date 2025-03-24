package SOLID_Principles.Dependeny_Inversion_Principle;

interface EmailClient {
    void sendEmail(String recipient, String subject, String body);
}

class GmailClient2 implements EmailClient{
    @Override
    public void sendEmail(String recipient, String subject, String body) {
        System.out.println("Use of Gmail Client");
        System.out.println("Email using sent to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

class OutLookClient2 implements EmailClient{
    @Override
    public void sendEmail(String recipient, String subject, String body) {
        System.out.println("Use of Outlook Client");
        System.out.println("Email using sent to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

class EmailService2 {
    EmailClient emailClient;

    EmailService2(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail(String recipient, String subject, String body) {
        emailClient.sendEmail(recipient, subject, body);
    }
}

public class AfterDIP {
    public static void main(String[] args) {
        GmailClient2 gmailClient2 = new GmailClient2();
        OutLookClient2 outLookClient2 = new OutLookClient2();

        EmailService2 emailService1 = new EmailService2(gmailClient2);
        EmailService2 emailService2 = new EmailService2(outLookClient2);

        emailService1.sendEmail("test@example.com", "Hello", "This is a test email.");
        System.out.println("\n-------------------------------------------------------\n");
        emailService2.sendEmail("test@example.com", "Hello", "This is a test email.");
    }
}
