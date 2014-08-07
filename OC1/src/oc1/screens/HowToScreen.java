package oc1.screens;

import oc1.screen.Screen;
import oc1.log.IssueReporter;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.GridLayout;
import oc1.ui.ActionButton;

/**
 *
 * @author Curt
 */
public final class HowToScreen
    extends Screen
{   
    public HowToScreen(Screen previous) {
        super("How To",previous);
    }
    
    @Override
    public void layoutForPortrait() {
        form.setLayout(new GridLayout(2,2));
        form.addComponent(submitIssueButton());
    }
    
    private Button submitIssueButton() {
        return new ActionButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
