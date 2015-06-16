package c1.ui;

import c1.command.C1LoggedCommand;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.animations.Transition;
import c1.log.C1IssueReporter;
import common.command.Command;

/**
 * A Form with lots of extra debugging.
 * @author Curt
 */
final class DebugForm
    extends Form
{
    final String title;

    private DebugForm(String title) {
        super(title);
        this.title = title;
    }

    public static DebugForm of(String title) {
        DebugForm form = new DebugForm(title);
        form.addCommand(submitIssue());
        return form;
    }

    private static com.codename1.ui.Command submitIssue() {
        return new C1LoggedCommand(new Command("Report") {
            @Override public void action(Object...args) {
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
        begin("paint"); super.paint(g); end("paint");
    }
    @Override public void paintBackground(Graphics g) {
        begin("paintBackground"); super.paintBackground(g); end("paintBackground");
    }
    @Override public void replace(Component current, Component next, Transition t) {
        begin("replace"); super.replace(current, next, t); end("replace");
    }
    @Override public void replaceAndWait(Component current, Component next, Transition t) {
        begin("replaceAndWait"); super.replaceAndWait(current, next, t); end("replaceAndWait");
    }

    private void begin(String message) {
        log(message + " {");
    }
    
    private void end(String message) {
        log("} " + message);
    }

    private void log(String message) {
        //LogManager.of().getLog(DebugForm.class).log(link + ":" + message);
    }

}
