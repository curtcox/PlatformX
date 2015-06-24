package an.a22.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.page.PageFactory;
import common.screen.Screen;
import common.page.PageLink;


public class AnApplication extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Registry.put(Context.class,this);
            AnRegistryLoader.load();
        } catch(Exception e){
            log(e);
        }
        show();
        log("onCreate(" +savedInstanceState + ") finished");
    }

    private void show() {
        PageFactory factory = Registry.get(PageFactory.class);
        Screen.show(PageLink.of(""), factory);
    }

    private void log(Object context) {
        getLog().log("init("+context +")");
    }

    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(AnApplication.class);
    }

}
