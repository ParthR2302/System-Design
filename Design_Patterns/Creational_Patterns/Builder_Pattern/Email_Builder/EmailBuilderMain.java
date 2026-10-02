package Design_Patterns.Creational_Patterns.Builder_Pattern.Email_Builder;

import java.util.ArrayList;
import java.util.List;

public class EmailBuilderMain {
    public static void main(String[] args) {
        EmailBuilder emailBuilder = new EmailBuilder();

        // Below is the step-by-step part of creating a complex object (Email) using the
        // Builder pattern.
        // We can set only the fields we want to set and then call build() to get the
        // final Email object.
        Email email = emailBuilder.setTo(new ArrayList<>(List.of("recipient@example.com")))
                .setSubject("Hello")
                .setBody("This is a test email.")
                .build();

        email.printEmail();

        System.out.println();

        List<String> recepients = new ArrayList<>(
                List.of("recepient1@mail.com", "recepient2@mail.com", "recepient6@mail.com"));
        String subject = "This is the Subject of the mail";
        List<String> ccList = new ArrayList<>(List.of("recepient3@mail.com", "recepient4@mail.com"));
        List<String> bccList = new ArrayList<>(List.of("recepient7@mail.com"));
        String body = "This is the body of the email.\nEmail of the body can contain multiple line.\nThanks and Regards";

        Email email2 = emailBuilder.setTo(recepients)
                .setSubject(subject)
                .setCc(ccList)
                .setBcc(bccList)
                .setBody(body)
                .build();

        email2.printEmail();
    }
}
