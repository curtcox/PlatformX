package com.codename1.ui;

import c1.command.C1LoggedCommand;
import com.codename1.ui.animations.Transition;
import x.log.ILog;
import x.log.ILogManager;
import x.Registry;
import x.command.Command;
import c1.log.C1IssueReporter;

/**
 * DebugForm in the same package, so we can intercept more methods.
 * @author Curt
 */
public final class UIDebugForm
    extends Form
{
    final String title;

    private UIDebugForm(String title) {
        super(title);
        this.title = title;
    }

    public static UIDebugForm of(String title) {
        UIDebugForm form = new UIDebugForm(title);
        form.addCommand(submitIssue());
        return form;
    }

    private static com.codename1.ui.Command submitIssue() {
        return new C1LoggedCommand(new Command("Report") {
            @Override
            protected void action(Object...args) {
                C1IssueReporter.sendEmail();
            }
        });
    }

    @Override protected void onShow()          { log("onShow"); }
    @Override protected void onShowCompleted() { log("onShowCompleted"); }
    @Override public void show() {
        begin("show"); super.show(); end("show");
    }
    @Override public void showBack() {
        begin("showBack"); super.showBack(); end("showBack");
    }
    @Override public void paint(Graphics g) {
        //begin("paint");
        super.paint(g);
        //end("paint");
    }
    @Override public void paintBackground(Graphics g) {
        //begin("paintBackground");
        super.paintBackground(g);
        //end("paintBackground");
    }
    @Override public void replace(Component current, Component next, Transition t) {
        begin("replace");
        super.replace(current, next, t);
        end("replace");
    }
    @Override public void replaceAndWait(Component current, Component next, Transition t) {
        begin("replaceAndWait"); super.replaceAndWait(current, next, t); end("replaceAndWait");
    }

    @Override void sizeChangedInternal(int w, int h) {
        begin("sizeChangedInternal(" + w + "," + h + ")");
        super.sizeChangedInternal(w, h);
        end("sizeChangedInternal");
    }
    
    private void begin(String message) {
        log(message + " {");
    }
    
    private void end(String message) {
        log("} " + message);
    }

    private void log(String message) {
        getLog().log(title + ":" + message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(UIDebugForm.class);
    }

}
