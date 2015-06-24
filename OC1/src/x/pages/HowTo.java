package x.pages;

import x.Registry;
import x.log.IIssueReporter;
import x.uiwidget.UIButton;

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
