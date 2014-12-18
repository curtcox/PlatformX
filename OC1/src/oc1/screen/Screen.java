package oc1.screen;

import com.codename1.ui.*;
import oc1.app.Registry;
import oc1.command.LoggedCommand;
import oc1.log.LogManager;
import oc1.ui.FormFactory;
import oc1.ui.IForm;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementers will need to override at least one layout method to create the UI.
 * @author Curt
 */
public abstract class Screen {

    public final IForm form;
    private final String name;
    private Screen previous; // set once
    private Command back;    // set once
    private static Screen showing; // the currently showing screen
    
    /**
     * Override this constructor to create a new screen.
     * @param name name of the Screen and title of the underlying Form
     */
    public Screen(String name) {
        this(FormFactory.of().newForm(name),name);
    }
    
    /**
     * This constructor is exposed mostly for testing.
     * @param form 
     */
    public Screen(IForm form, String name) {
        this.form = form;
        this.name = name;
        log("created " + name);
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
        log("show " + name);
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
        log("back " + name);
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
    public void refresh() {
        layoutForm();
    }
    
    public boolean isPortrait() {
        return Registry.get(Display.class).isPortrait();
    }


    final public void layoutForm() {
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
        form.layout(layout);
    }

    private void log(String message) {
        LogManager.of().getLog(Screen.class).log(message);    
    }
}
