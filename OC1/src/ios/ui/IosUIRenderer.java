package ios.ui;

import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
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
//        final XButton button = (XButton) layout;
//        Button aButton = new Button(context());
//        aButton.setText(button.text);
//        aButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button.onTap();
//            }
//        });
        return null;
    }

    static UILabel label(XComponent layout) {
//        XLabel label = (XLabel) layout;
//        TextView aLabel = new TextView(context());
//        aLabel.setText(label.text);
//        return aLabel;
        return null;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosUIRenderer.class);
    }

}
