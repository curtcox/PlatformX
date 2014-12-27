package c1.ui;

import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.Layout;
import common.ICommand;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLayout;
import common.screen.ScreenLink;
import c1.Components;
import c1.command.LoggedCommand;

public class C1Form
    extends Form
    implements IForm
{
    public C1Form(String title) {
        super(title);
        addCommand(goHome());
        refreshOnOrientationChange();
        refreshOnPull();
    }

    private static Command goHome() {
        return new LoggedCommand("Home") {
            @Override protected void go() {
                ScreenFactory.DEFAULT.create(new ScreenLink("")).show();
            }
        };
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

    public void layout(ScreenLayout layout) {
        removeAll();
        setLayout(createLayout(layout));
        for(Object object : layout.components) {
            Components.addToContainer(component(object), this);
        }
    }

    private Layout createLayout(ScreenLayout layout) {
        return null;
    }

    private Component component(Object object) {
        return null;
    }

    @Override
    public void setBackCommand(ICommand back) {
        super.setBackCommand((Command) back);
    }

}
