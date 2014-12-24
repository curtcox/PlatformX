package oc1.app;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

import common.ILog;
import common.ILogManager;
import common.Registry;
import oc1.log.LogManager;
import oc1.screen.ScreenFactory;
import oc1.screen.ScreenLink;

public class MyApplication {

    private Form current;

    public void init(Object context) {
        try {
            loadTheme();
            RegistryLoader.load();
            ExceptionLogger.of().install();
        } catch(IOException e){
            log(e);
        }
    }

    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(MyApplication.class);
    }

    private void loadTheme() throws IOException {
        Resources theme = Resources.openLayered("/theme");
        setThemeProps(theme);
        Registry.put(Resources.class, theme);
    }

    private UIManager getUIManager() {
       return UIManager.getInstance();
    }
    
    private Display getDisplay() {
       return Display.getInstance();
    }
    
    private void setThemeProps(Resources theme) throws IOException {
        getUIManager().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
    }

    public void start() {
        if (current != null){
            current.show();
            return;
        }
        HeartbeatMonitor.install();
        show();
    }

    private void show() {
        ScreenFactory factory = Registry.get(ScreenFactory.class);
        factory.create(new ScreenLink("")).show();
    }
    
    public void stop() {
        current = getDisplay().getCurrent();
    }
    
    public void destroy() {}

}
