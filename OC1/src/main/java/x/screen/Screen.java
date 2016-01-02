package x.screen;

import x.app.Registry;
import x.command.XCommand;
import x.event.LiveList;
import x.event.NamedValueListSource;
import x.event.XLiveList;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.ui.IDisplay;
import x.ui.IForm;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;
import x.util.NamedValue;

import java.util.Arrays;

/**
 * The entire UI, as presented to the user, at a specific time.
 * There is a 1-to-1 relationship between ScreenS and PagesS.
 * Only one Screen is showing at a time.
 */
public final class Screen
    implements NamedValueListSource
{

    public final IForm form;
    public final PageLink link;
    private Screen previous; // set once
    private XCommand back;    // set once
    public final Page page;
    private static Screen showing; // the currently showing screen
    
    /**
     * This constructor to create a new screen.
     */
    public static Screen of(Page page) {
        return new Screen(formFactory().newForm(page.link),page.link,page);
    }

    private Screen(IForm form, PageLink link, Page page) {
        this.form = form;
        this.link = link;
        this.page = page;
        classLog().log("created ", link, page);
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

    private XCommand backCommand() {
        return new XCommand("Back to " + previous.link) {
            @Override public void action(Object... args) {
                back();
            }
        };
    }

    public void show() {
        try {
            log("show",link);
            setPrevious();
            showing = this;
            refresh();
            form.show();
            setBackOnForm();
        } catch (RuntimeException e) {
            log(e);
        }
    }

    public static void show(PageLink link) {
        if (link.page!=null) {
            show(link.page);
        } else {
            show(link, pageFactory().create(link));
        }
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
        Screen screen = of(page);
        screen.show();
    }

    private static PageFactory pageFactory() {
        return Registry.get(PageFactory.class);
    }

    public static Screen getShowing() {
        return showing;
    }
    
    public final void back() {
        log("back",link);
        if (previous!=null) {
            previous.goBackToThisScreen();
        }
    }
    
    private void goBackToThisScreen() {
        showing = this;
        refresh();
        layoutForm();
        form.showBack();
        setBackOnForm();
    }

    private void setBackOnForm() {
        if (back != null) {
            form.setBackCommand(back);
        }
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

    private void log(String message, Object... details) {
        log().log(message, details);
    }

    private static void log(Exception e) {
        classLog().log(e);
    }

    private static ILog classLog() {
        return logManager().getLog(Screen.class);
    }

    private ILog log() {
        return logManager().getLog(this);
    }

    private static ILogManager logManager() {
        return Registry.get(ILogManager.class);
    }

    @Override
    public LiveList<NamedValue> asNamedValues() {
        return XLiveList.of(Arrays.asList(
                new NamedValue("form", form),
                new NamedValue("link", link),
                new NamedValue("previous", previous),
                new NamedValue("back", back),
                new NamedValue("page", page)
        ));
    }

    @Override
    public String toString() {
        return "page=" + page + "link=" +link;
    }
}
