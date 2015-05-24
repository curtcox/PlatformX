package common.screens;

import common.Registry;
import common.log.IIssueReporter;
import common.uiwidget.UIButton;

public final class HowTo {   
    
    UIButton submitIssueButton() {
        return new UIButton("Submit Issue") {
            @Override public void onTap() { issueReporter().sendEmail(); }
        };
    }

    private IIssueReporter issueReporter() {
        return Registry.get(IIssueReporter.class);
    }
}
