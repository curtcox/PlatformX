package c1.ui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import static com.codename1.ui.layouts.BoxLayout.*;
import common.uiwidget.*;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;

final class C1UIRenderer {
    static Component render(UIComponent layout) {
        if (layout instanceof UIButton)  { return button(layout); }
        if (layout instanceof UILabel)   { return label(layout);  }
        if (layout instanceof UIFlow)    { return flow(layout);  }
        if (layout instanceof UIColumn)  { return column(layout);  }
        if (layout instanceof UIRow)     { return row(layout);  }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    static Container column(UIComponent layout) {
        return box(layout,Y_AXIS);
    }

    static Container row(UIComponent layout) {
        return box(layout,X_AXIS);
    }

    static Container box(UIComponent layout,int axis) {
        Container panel = new Container();
        panel.setLayout(new BoxLayout(axis));
        for (UIComponent component : ((UIContainer) layout).components) {
            panel.addComponent(render(component));
        }
        return panel;
    }

    static Container flow(UIComponent layout) {
        Container panel = new Container();
        for (UIComponent component : ((UIContainer) layout).components) {
            panel.addComponent(render(component));
        }
        return panel;
    }

    static Button button(UIComponent layout) {
        final UIButton button = (UIButton) layout;
        Button cButton = new Button(button.text);
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button.onTap();
            }
        });
        return cButton;
    }

    static Label label(UIComponent layout) {
        UILabel label = (UILabel) layout;
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
