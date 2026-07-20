package Design_Patterns.Creational_Patterns.Builder_Pattern.Email_Builder;

import java.util.List;

public class EmailBuilder {
    private String to;
    private String subject;
    private String body;
    private String cc;
    private String bcc;
    private List<String> attachments;

    // setters
    // build() method - IMPORTANT
    // getters

    public EmailBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public EmailBuilder setCc(String cc) {
        this.cc = cc;
        return this;
    }

    public EmailBuilder setBcc(String bcc) {
        this.bcc = bcc;
        return this;
    }

    public EmailBuilder setAttachments(List<String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Email build() {
        if(to == null || to.isEmpty()) {
            throw new IllegalArgumentException("Recipient email address (to) is required.");
        }

        return new Email(this);
    }

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
}
