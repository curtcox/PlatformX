package x.pages;

import x.Registry;
import x.log.IIssueReporter;
import x.uiwidget.XButton;

public final class HowTo {   
    
    XButton submitIssueButton() {
        return new XButton("Submit Issue") {
            @Override public void onTap() { issueReporter().sendEmail(); }
        };
    }

    private IIssueReporter issueReporter() {
        return Registry.get(IIssueReporter.class);
    }
}
