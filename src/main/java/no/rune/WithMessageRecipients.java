package no.rune;

import java.util.List;

public interface WithMessageRecipients {

    <CONTACT extends WithName & WithEmailAddress> List<? extends CONTACT> getMessageRecipients();

}
