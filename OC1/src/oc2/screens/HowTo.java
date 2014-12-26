package oc2.screens;

import oc1.log.IssueReporter;
import oc1.ui.ActionButton;

public final class HowTo {   
    
    ActionButton submitIssueButton() {
        return new ActionButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
