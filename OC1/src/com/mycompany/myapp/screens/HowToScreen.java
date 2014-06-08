package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.ui.ActionButton;

/**
 *
 * @author Curt
 */
final class HowToScreen
    extends Screen
{
    
    
    HowToScreen(Screen previous) {
        super("How To",previous);
        form.setLayout(new GridLayout(2,2));
        form.addComponent(submitIssueButton());
    }
    
    private Button submitIssueButton() {
        return new ActionButton("Submit Issue") {
            @Override public void onTap() { IssueReporter.sendEmail(); }
        };
    }

}
