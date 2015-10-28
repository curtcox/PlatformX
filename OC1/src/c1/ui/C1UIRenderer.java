package c1.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

import static com.codename1.ui.layouts.BoxLayout.X_AXIS;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;

final class C1UIRenderer {

    static Component render(XComponent layout) {
        if (layout instanceof XPeeredComponent) { return peer(layout);}
        if (layout instanceof XButton)          { return button(layout); }
        if (layout instanceof XLabel)           { return label(layout);  }
        if (layout instanceof XFlow)            { return flow(layout);  }
        if (layout instanceof XColumn)          { return column(layout);  }
        if (layout instanceof XRow)             { return row(layout);  }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    private static Component peer(XComponent layout) {
        XPeeredComponent peeredComponent = (XPeeredComponent) layout;
        return (Component) peeredComponent.peer;
    }

    static Container column(XComponent layout) {
        return box(layout,Y_AXIS);
    }

    static Container row(XComponent layout) {
        return box(layout,X_AXIS);
    }

    static Container box(XComponent layout,int axis) {
        Container panel = new Container();
        panel.setLayout(new BoxLayout(axis));
        for (XComponent component : ((XContainer) layout).components) {
            panel.addComponent(render(component));
        }
        return panel;
    }

    static Container flow(XComponent layout) {
        Container panel = new Container();
        for (XComponent component : ((XContainer) layout).components) {
            panel.addComponent(render(component));
        }
        return panel;
    }

    static Button button(XComponent layout) {
        final XButton button = (XButton) layout;
        Button cButton = new Button(button.text);
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button.onTap();
            }
        });
        return cButton;
    }

    static Label label(XComponent layout) {
        XLabel label = (XLabel) layout;
        Label cLabel = new Label(label.text);
        return cLabel;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1UIRenderer.class);
    }
}
