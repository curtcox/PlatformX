package va.ui;

import com.vaadin.ui.UI;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IDisplay;
import x.ui.IForm;

public final class VaDisplay
    implements IDisplay
{
    private VaForm form;
    private static VaDisplay singleton;

    private VaDisplay() {}

    public static VaDisplay of() {
        if (singleton==null) {
            singleton = newDisplay();
        }
        return singleton;
    }

    private static VaDisplay newDisplay() {
        return new VaDisplay();
    }

    @Override
    public boolean isPortrait() {
        return true;
    }

    @Override
    public IForm getCurrent() {
        return form;
    }

    void show(VaForm form) {
        log("show " + form + " on " + ui());
        this.form = form;
        ui().setContent(form);
    }

    UI ui() {
        return Registry.get(UI.class);
    }

    @Override
    public void execute(String url) {
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaDisplay.class,this);
    }
}
