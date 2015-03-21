package c1.ui;

import c1.command.C1LoggedCommand;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import c1.Components;
import common.command.Command;
import common.ui.IForm;
import common.uiwidget.UIComponent;

public class C1Form
    extends Form
    implements IForm
{
    private final ScreenLink link;

    public C1Form(ScreenLink link) {
        super(link.title());
        this.link = link;
        addCommand(goHome());
        refreshOnOrientationChange();
        refreshOnPull();
    }

    private static com.codename1.ui.Command goHome() {
        return new C1LoggedCommand(new Command("Home") {
            @Override public void action(Object...args) {
                ScreenFactory.DEFAULT.create(ScreenLink.of("")).show();
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

    public void layout(UIComponent component) {
        removeAll();
        setLayout(createLayout(component));
        Components.addToContainer(component(component), this);
    }

    private Layout createLayout(UIComponent component) {
        return new FlowLayout();
    }

    private Component component(UIComponent component) {
        return new Label();
    }

    @Override
    public void setBackCommand(Command back) {
        super.setBackCommand(new C1LoggedCommand(back));
    }

    @Override
    public ScreenLink getScreenLink() {
        return link;
    }

}
