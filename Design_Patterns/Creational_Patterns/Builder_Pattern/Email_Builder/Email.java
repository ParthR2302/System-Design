package Design_Patterns.Creational_Patterns.Builder_Pattern.Email_Builder;

import java.util.List;

// We want to keep the Email object immutable, so we will not provide any setters in the Email class. Instead, we will use the EmailBuilder class to construct the Email object.

public class Email {
    private String to;
    private String subject;
    private String body;
    private String cc;
    private String bcc;
    private List<String> attachments;

    Email(EmailBuilder builder) {
        this.to = builder.getTo();
        this.subject = builder.getSubject();
        this.body = builder.getBody();
        this.cc = builder.getCc();
        this.bcc = builder.getBcc();
        this.attachments = builder.getAttachments();
    }
    
    // getters

    public String getTo() {
        return to;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public String getCc() {
        return cc;
    }
    public String getBcc() {
        return bcc;
    }
    public List<String> getAttachments() {
        return attachments;
    }

    public void printEmail() {
        System.out.println("To: " + to);
        if(subject == null || subject.isEmpty()) {
            System.out.println("Subject: (No Subject)");
        } else {
            System.out.println("Subject: " + subject);
        }
        if(body == null || body.isEmpty()) {
            System.out.println("Body: (No Body)");
        } else {
            System.out.println("Body: " + body);        
        }
        if(cc == null || cc.isEmpty()) {
            System.out.println("CC: (No CC)");
        } else {
            System.out.println("CC: " + cc);
        }
        if(bcc == null || bcc.isEmpty()) {
            System.out.println("BCC: (No BCC)");
        } else {
            System.out.println("BCC: " + bcc);
        }
        if(attachments == null || attachments.isEmpty()) {
            System.out.println("Attachments: (No Attachments)");
        } else {
            System.out.println("Attachments: " + attachments);
        }
    }
    
}
