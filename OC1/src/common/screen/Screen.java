package common.screen;

import common.command.Command;
import common.log.ILog;
import common.log.ILogManager;
import common.Registry;
import common.ui.IDisplay;
import common.ui.IForm;
import common.ui.IFormFactory;
import common.uiwidget.UIComponent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementers will need to override at least one layout method to create the UI.
 * @author Curt
 */
public abstract class Screen {

    public final IForm form;
    public final ScreenLink link;
    private Screen previous; // set once
    private Command back;    // set once
    private static Screen showing; // the currently showing screen
    
    /**
     * Override this constructor to create a new screen.
     * @param link to the Screen
     */
    public Screen(ScreenLink link) {
        this(formFactory().newForm(link),link);
    }

    /**
     * This constructor is exposed mostly for testing.
     * @param form 
     */
    public Screen(IForm form, ScreenLink link) {
        this.form = form;
        this.link = link;
        log("created " + link);
    }

    private static IFormFactory formFactory() {
        return Registry.get(IFormFactory.class);
    }

    private void setPrevious() {
        if (previous!=null || showing==null) {
            return;
        }
        previous = showing;
        back = backCommand();
    }

    private Command backCommand() {
        return new Command("Back") {
            @Override public void action(Object... args) {
                back();
            }
        };
    }

    public void show() {
        log("show " + link.toString());
        setPrevious();
        showing = this;
        refresh();
        form.show();
        if (back!=null) {
            form.setBackCommand(back);
        }
    }

    public static void show(ScreenLink link, ScreenFactory factory) {
        Screen[] screens = factory.create(link);
        if (screens.length==1) {
            screens[0].show();
        }
    }

    public static Screen getShowing() {
        return showing;
    }
    
    public void back() {
        log("back " + link.toString());
        if (previous!=null) {
            previous.goBackToThisScreen();
        }
    }
    
    private void goBackToThisScreen() {
        showing = this;
        refresh();
        layoutForm();
        form.showBack();
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
        return Registry.get(IDisplay.class).isPortrait();
    }

    final public void layoutForm() {
        if (isPortrait()) {
            layout(layoutForPortrait());
        } else {
            layout(layoutForLandscape());
        }
    }

    protected abstract UIComponent layoutForPortrait();

    protected UIComponent layoutForLandscape() {
        return layoutForPortrait();
    }

    private void layout(UIComponent layout) {
        form.layout(layout);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Screen.class);
    }

}
