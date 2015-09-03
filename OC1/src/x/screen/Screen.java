package x.screen;

import x.Registry;
import x.command.Command;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.ui.IDisplay;
import x.ui.IForm;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * There is a 1-to-1 relationship between ScreenS and PagesS.
 * Only one Screen is showing at a time.
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
        show(link, pageFactory().create(link));
    }

    public static void show(PageLink link, Page[] pages) {
        if (pages.length==0) {
            throw loggedException("No pages found for " + link);
        }
        if (pages.length>1) {
            throw loggedException("Multiple pages (" + pages.length + ") found for " + link);
        }
        show(pages[0]);
    }

    private static RuntimeException loggedException(String message) {
        RuntimeException exception = new RuntimeException(message);
        log(exception);
        return exception;
    }

    private static void show(Page page) {
        Screen screen = of(page.link,page);
        screen.show();
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

    private XComponent layoutForPortrait() {
        return page.layoutForPortrait();
    }

    protected XComponent layoutForLandscape() {
        return page.layoutForLandscape();
    }

    private void layout(XComponent layout) {
        form.layout(layout);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private static void log(Exception e) {
        getLog().log(e);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Screen.class);
    }

}
