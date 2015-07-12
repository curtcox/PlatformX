package va.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

final class VaUIRenderer {

    static Component render(XComponent layout) {
        if (layout instanceof XPeeredComponent)  { return peer(layout);   }
        if (layout instanceof XButton)           { return button(layout); }
        if (layout instanceof XLabel)            { return label(layout);  }
        if (layout instanceof XFlow)             { return flow(layout);   }
        if (layout instanceof XColumn)           { return column(layout); }
        if (layout instanceof XRow)              { return row(layout);    }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    private static Component peer(XComponent layout) {
        XPeeredComponent peered = (XPeeredComponent) layout;
        return (Component) peered.peer;
    }

    static Component column(XComponent layout) {
        return box(layout);
    }

    static Component row(XComponent layout) {
        return box(layout);
    }

    static Component box(XComponent layout) {
        for (XComponent component : ((XContainer) layout).components) {
        }
        return null;
    }

    static Component flow(XComponent layout) {
        for (XComponent component : ((XContainer) layout).components) {
        }
        return null;
    }

    static Component button(XComponent layout) {
        final XButton button = (XButton) layout;
        Button vButton = new Button();
        vButton.setCaption(button.text);
        vButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                button.onTap();
            }
        });
        return vButton;
    }

    static Component label(XComponent layout) {
        XLabel label = (XLabel) layout;
        Label vLabel = new Label();
        vLabel.setValue(label.text);
        return vLabel;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaUIRenderer.class);
    }

}
