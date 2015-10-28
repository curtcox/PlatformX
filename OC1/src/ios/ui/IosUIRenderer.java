package ios.ui;

import ios.uiwidget.IosButtonViewController;
import ios.uiwidget.IosLabelViewController;
import org.robovm.apple.uikit.*;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

final class IosUIRenderer {

    static UIViewController render(XComponent layout) {
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

    private static UIViewController peer(XComponent layout) {
        XPeeredComponent peered = (XPeeredComponent) layout;
        return (UIViewController) peered.peer;
    }

    static UICollectionViewController column(XComponent layout) {
        return box(layout);
    }

    static UICollectionViewController row(XComponent layout) {
        return box(layout);
    }

    static UICollectionViewController box(XComponent layout) {
        UICollectionViewController box = new UICollectionViewController();
        for (XComponent component : ((XContainer) layout).components) {
        }
        return box;
    }

    static UICollectionViewController flow(XComponent layout) {
        UICollectionViewController box = new UICollectionViewController();
        for (XComponent component : ((XContainer) layout).components) {
        }
        return box;
    }

    static IosButtonViewController button(XComponent layout) {
        final XButton button = (XButton) layout;
        return IosButtonViewController.of(button);
    }

    static IosLabelViewController label(XComponent layout) {
        XLabel label = (XLabel) layout;
        return IosLabelViewController.of(label);
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(null,IosUIRenderer.class);
    }

}
