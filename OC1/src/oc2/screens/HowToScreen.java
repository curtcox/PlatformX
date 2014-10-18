package oc2.screens;

import oc1.screen.*;
import oc1.log.IssueReporter;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.GridLayout;
import oc1.screen.ScreenLink;
import oc1.ui.ActionButton;

public final class HowToScreen
    extends Screen
{   
    public static ScreenFactory FACTORY = new GlobScreenFactory("HowTo") {
        public Screen doCreate(ScreenLink link) {
            return new HowToScreen();
        }     
    };
    
    HowToScreen() {
        super("How To");
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
