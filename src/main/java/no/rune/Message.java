package no.rune;

import java.util.List;

public class Message implements WithMessageRecipients {

    private final List<EmailRecipient> recipients;

    public Message(EmailRecipient ... recipients) {
        this.recipients = List.of(recipients);
    }

    @Override
    public List<EmailRecipient> getMessageRecipients() {
        return recipients;
    }
}
