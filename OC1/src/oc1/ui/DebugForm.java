package oc1.ui;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import oc1.log.IssueReporter;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
public final class DebugForm
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
    
    @Override protected void onShow() { log("onShow"); }
    @Override protected void onShowCompleted() { log("onShowCompleted"); }
    
    private void log(String message) {
        LogManager.of().getLog(DebugForm.class).log(title + ":" + message);    
    }
    
    private static Command submitIssue() {
        return new Command("Report") {
            @Override
            public void actionPerformed(ActionEvent event) {
                IssueReporter.sendEmail();
            }
        };
    }

}
