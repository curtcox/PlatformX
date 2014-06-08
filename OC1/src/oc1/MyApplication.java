package oc1;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import oc1.log.LogManager;
import oc1.screens.HomeScreen;
import java.io.IOException;

public class MyApplication {

    private Form current;

    public void init(Object context) {
        try {
            loadTheme();
            RegistryLoader.load();
        } catch(IOException e){
            log(e);
        }
    }

    private void log(Exception e) {
        LogManager.of().getLog(MyApplication.class).log(e);    
    }
    
    private void loadTheme() throws IOException {
        Resources theme = Resources.openLayered("/theme");
        setTheme(theme);
        Registry.put(Resources.class, theme);
    }

    private void setTheme(Resources theme) throws IOException {
        UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
    }

    public void start() {
        if (current != null){
            current.show();
            return;
        }
        HomeScreen.showInitial();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {}

}
