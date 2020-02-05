package no.rune;

import org.junit.jupiter.api.Test;

class DemonstrateBrokenMethodDispatchTest {

    @Test
    void broken_method_dispatch_on_bounded_type_in_lambda_argument() {
        WithMessageRecipients withRecipients = new Message(new EmailRecipient("Jane", "jane@example.com"), new EmailRecipient("Joe", "joe@example.com"));

        withRecipients.getMessageRecipients()
            .stream()
            .forEach(recipient -> System.out.println(recipient.getName() + " <" + recipient.getEmailAddress() + ">"));
    }



    @Test
    void works_fine_in_for_loop() {
        WithMessageRecipients withRecipients = new Message(new EmailRecipient("Jane", "jane@example.com"), new EmailRecipient("Joe", "joe@example.com"));

        for (var recipient : withRecipients.getMessageRecipients()) {
            System.out.println(recipient.getName() + " <" + recipient.getEmailAddress() + ">");
        }
    }

}
