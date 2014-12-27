package c1.screens;

import c1.log.IssueReporter;
import c1.ui.ActionButton;

public final class HowTo {   
    
    ActionButton submitIssueButton() {
        return new ActionButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
