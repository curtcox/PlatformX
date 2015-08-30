package va.ui;

import com.vaadin.ui.UI;
import x.Registry;
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
        this.form = form;
        ui().setContent(form);
    }

    UI ui() {
        return Registry.get(UI.class);
    }

    @Override
    public void execute(String url) {
    }
}
