package no.rune;

public class EmailRecipient implements WithName, WithEmailAddress {

    private final String name;
    private final String emailAddress;

    public EmailRecipient(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getName() {
        return name;
    }

}
