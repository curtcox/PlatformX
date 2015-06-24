package c1.ui;

import c1.Components;
import c1.command.C1LoggedCommand;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import x.command.Command;
import x.page.PageLink;
import x.screen.Screen;
import x.ui.IForm;
import x.uiwidget.UIComponent;

/**
 * A CodenameOne Form that is also an IForm.
 */
public class C1Form
    extends Form
    implements IForm
{
    private final PageLink link;

    public C1Form(PageLink link) {
        super(link.title());
        this.link = link;
        addCommand(goHome());
        refreshOnOrientationChange();
        refreshOnPull();
    }

    private static com.codename1.ui.Command goHome() {
        return new C1LoggedCommand(new Command("Home") {
            @Override public void action(Object...args) {
                Screen.show(PageLink.of(""));
            }
        });
    }

    private void refreshOnOrientationChange() {
        addOrientationListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                refresh();
            }
        });
    }

    private void refreshOnPull() {
        getContentPane().addPullToRefresh(new Runnable(){
            public void run() {
                refresh();
            }
        });
    }

    private void refresh() {
        Screen.getShowing().refresh();
    }

    final public void layout(UIComponent component) {
        removeAll();
        setLayout(createLayout());
        Components.addToContainer(C1UIRenderer.render(component), this);
    }

    private Layout createLayout() {
        return new FlowLayout();
    }

    @Override
    final public void setBackCommand(Command back) {
        super.setBackCommand(new C1LoggedCommand(back));
    }

    @Override
    final public PageLink getScreenLink() {
        return link;
    }

}
