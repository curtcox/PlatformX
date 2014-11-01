package oc2.screens;

import oc1.screen.*;
import oc1.log.IssueReporter;
import com.codename1.ui.Button;
import oc1.ui.ActionButton;

public final class HowToScreenController
    extends ScreenController
{   
    
    @Override
    public void addSpecifics(ScreenContext context) {
        context.put("submit_issue",new Getter() { public Object get() {
            return submitIssueButton();
        }});
    }
    
    private Button submitIssueButton() {
        return new ActionButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
