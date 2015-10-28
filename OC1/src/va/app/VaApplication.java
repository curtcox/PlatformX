package va.app;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.page.PageLink;
import x.screen.Screen;

public final class VaApplication
        extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            Registry.put(UI.class,this);
            VaRegistryLoader.load();
        } catch (Exception e) {
            log(e);
        }
        show();
        log("init(" + vaadinRequest + ") finished");
    }

    private void show() {
        Screen.show(PageLink.of(""));
    }

    private void log(String message) {
        getLog().log(message);
    }
    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaApplication.class,this);
    }

}