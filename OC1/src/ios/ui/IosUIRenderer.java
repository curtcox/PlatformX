package ios.ui;

import org.robovm.apple.uikit.*;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

final class IosUIRenderer {

    static UIView render(XComponent layout) {
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

    private static UIView peer(XComponent layout) {
        XPeeredComponent peered = (XPeeredComponent) layout;
        return (UIView) peered.peer;
    }

    static UIView column(XComponent layout) {
        return box(layout);
    }

    static UIView row(XComponent layout) {
        return box(layout);
    }

    static UIView box(XComponent layout) {
        for (XComponent component : ((XContainer) layout).components) {
        }
        return null;
    }

    static UIView flow(XComponent layout) {
        for (XComponent component : ((XContainer) layout).components) {
        }
        return null;
    }

    static UIButton button(XComponent layout) {
        final XButton button = (XButton) layout;
        UIButton uiButton = new UIButton();
        uiButton.setTitle(button.text,null);
        uiButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside (UIControl control, UIEvent event) {
                button.onTap();
            }
        });
        return uiButton;
    }

    static UILabel label(XComponent layout) {
        XLabel label = (XLabel) layout;
        UILabel aLabel = new UILabel();
        aLabel.setText(label.text);
        return aLabel;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosUIRenderer.class);
    }

}
