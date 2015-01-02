package c1.screens;

import c1.log.IssueReporter;
import common.ui.UIButton;

public final class HowTo {   
    
    UIButton submitIssueButton() {
        return new UIButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
