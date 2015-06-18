package an.a22.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;


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
        ScreenFactory factory = Registry.get(ScreenFactory.class);
        Screen.show(ScreenLink.of(""), factory);
    }

    private void displayUI() {
        TextView label = new TextView(this);
        label.setText("Hello World");
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(label);

        setContentView(layout);
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
