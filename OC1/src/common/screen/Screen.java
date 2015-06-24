package common.screen;

import common.Registry;
import common.command.Command;
import common.log.ILog;
import common.log.ILogManager;
import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.ui.IDisplay;
import common.ui.IForm;
import common.ui.IFormFactory;
import common.uiwidget.UIComponent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementers will need to override at least one layout method to create the UI.
 */
public final class Screen {

    public final IForm form;
    public final PageLink link;
    private Screen previous; // set once
    private Command back;    // set once
    final Page page;
    private static Screen showing; // the currently showing screen
    
    /**
     * This constructor to create a new screen.
     */
    public static Screen of(PageLink link, Page page) {
        return new Screen(formFactory().newForm(link),link,page);
    }

    /**
     * This constructor is exposed mostly for testing.
     */
    private Screen(IForm form, PageLink link, Page page) {
        this.form = form;
        this.link = link;
        this.page = page;
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
        try {
            log("show " + link.toString());
            setPrevious();
            showing = this;
            refresh();
            form.show();
            if (back != null) {
                form.setBackCommand(back);
            }
        } catch (RuntimeException e) {
            log(e);
        }
    }

    public static void show(PageLink link) {
        Page[] screens = pageFactory().create(link);
        if (screens.length==0) {
            throw new RuntimeException("No pages found for " + link);
        }
        if (screens.length==1) {
            Page page = screens[0];
            Screen screen = of(link,page);
            screen.show();
        }
    }

    private static PageFactory pageFactory() {
        return Registry.get(PageFactory.class);
    }

    public static Screen getShowing() {
        return showing;
    }
    
    public final void back() {
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
     * It can also be called when the screen needs to be refreshed to reflect a change.
     */
    public void refresh() {
        page.refresh();
        layoutForm();
    }
    
    final boolean isPortrait() {
        return Registry.get(IDisplay.class).isPortrait();
    }

    final public void layoutForm() {
        if (isPortrait()) {
            layout(layoutForPortrait());
        } else {
            layout(layoutForLandscape());
        }
    }

    private UIComponent layoutForPortrait() {
        return page.layoutForPortrait();
    }

    protected UIComponent layoutForLandscape() {
        return page.layoutForLandscape();
    }

    private void layout(UIComponent layout) {
        form.layout(layout);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Screen.class);
    }

}
