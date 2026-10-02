package Design_Patterns.Creational_Patterns.Builder_Pattern.Email_Builder;

import java.util.List;

public class EmailBuilder {
    private List<String> to;
    private String subject;
    private String body;
    private List<String> cc;
    private List<String> bcc;
    private List<String> attachments;

    // setters
    // getters
    // build() method - IMPORTANT

    public EmailBuilder setTo(List<String> to) {
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

    public EmailBuilder setCc(List<String> cc) {
        this.cc = cc;
        return this;
    }

    public EmailBuilder setBcc(List<String> bcc) {
        this.bcc = bcc;
        return this;
    }

    public EmailBuilder setAttachments(List<String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getCc() {
        return cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public Email build() {
        if(to == null || to.isEmpty()) {
            throw new IllegalArgumentException("Recipient(s) email address is required.");
        }

        return new Email(this);
    }
}
