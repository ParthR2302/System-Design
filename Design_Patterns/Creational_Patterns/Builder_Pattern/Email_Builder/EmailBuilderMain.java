package Design_Patterns.Creational_Patterns.Builder_Pattern.Email_Builder;

public class EmailBuilderMain {
    public static void main(String[] args) {
        EmailBuilder emailBuilder = new EmailBuilder();

        // Below is the step-by-step part of creating a complex object (Email) using the Builder pattern. 
        // We can set only the fields we want to set and then call build() to get the final Email object.
        Email email = emailBuilder.setTo("recipient@example.com")
                .setSubject("Hello")
                .setBody("This is a test email.")
                .build();

        email.printEmail();
    }
}
