package oc1.screen;

import com.codename1.ui.*;
import com.codename1.ui.events.*;
import oc1.app.Registry;
import oc1.command.LoggedCommand;
import oc1.log.LogManager;
import oc1.ui.FormFactory;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementors will need to override at least one layout method to create the UI.
 * @author Curt
 */
public abstract class Screen {

    public final Form form;
    private Screen previous; // set once
    private Command back;    // set once
    private static Screen showing; // the currently showing screen
    
    /**
     * Override this constructor to create a new screen.
     * @param name name of the Screen and title of the underlying Form
     */
    public Screen(String name) {
        this(FormFactory.of().newForm(name));
    }
    
    /**
     * This constructor is exposed mostly for testing.
     * @param form 
     */
    public Screen(Form form) {
        this.form = form;
        refreshOnOrientationChange();
        refreshOnPull();
        log("created " + form.getTitle());
    }

    private void setPrevious() {
        if (previous!=null || showing==null) {
            return;
        }
        previous = showing;
        back = new LoggedCommand("Back") {
            @Override protected void go() {
                back();
            }
        };
    }

    public void show() {
        log("show " + form.getTitle());
        setPrevious();
        showing = this;
        refresh();
        layoutForm();
        form.show();
        form.setBackCommand(back);
    }
   
    public static Screen getShowing() {
        return showing;
    }
    
    public void back() {
        log("back " + form.getTitle());
        if (previous!=null) {
            goBack();
        }
    }
    
    private void goBack() {
        previous.refresh();
        previous.layoutForm();
        previous.form.showBack();
    }

    /**
     * This is called whenever the screen is shown.
     * Override it in order to update any screen state that might have
     * changed since the last showing.
     */
    protected void refresh() {
        layoutForm();
    }
    
    public boolean isPortrait() {
        return Registry.get(Display.class).isPortrait();
    }

    private void refreshOnOrientationChange() {
        form.addOrientationListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                refresh();
            }
        });
    }

    private void refreshOnPull() {
        form.getContentPane().addPullToRefresh(new Runnable(){
            public void run() {
                Screen.getShowing().refresh();
            }
        });
    }

    final public void layoutForm() {
        form.removeAll();
        if (isPortrait()) {
            layout(layoutForPortrait());
        } else {
            layout(layoutForLandscape());
        }
    }

    protected abstract ScreenLayout layoutForPortrait();

    protected ScreenLayout layoutForLandscape() {
        return layoutForPortrait();
    }

    private void layout(ScreenLayout layout) {
        form.setLayout(layout.layout);
        for(Component component : layout.components) {
            Components.addToContainer(component, form);
        }
    }

    private void log(String message) {
        LogManager.of().getLog(Screen.class).log(message);    
    }
}
