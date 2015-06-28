package ios.ui;

import org.robovm.apple.uikit.UIView;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

final class IosUIRenderer {

    static UIView render(UIComponent layout) {
        if (layout instanceof UIPeeredComponent)  { return peer(layout);   }
        if (layout instanceof UIButton)           { return button(layout); }
        if (layout instanceof UILabel)            { return label(layout);  }
        if (layout instanceof UIFlow)             { return flow(layout);   }
        if (layout instanceof UIColumn)           { return column(layout); }
        if (layout instanceof UIRow)              { return row(layout);    }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    private static UIView peer(UIComponent layout) {
        UIPeeredComponent peered = (UIPeeredComponent) layout;
        return (UIView) peered.peer;
    }

    static UIView column(UIComponent layout) {
        return box(layout);
    }

    static UIView row(UIComponent layout) {
        return box(layout);
    }

    static UIView box(UIComponent layout) {
        for (UIComponent component : ((UIContainer) layout).components) {
        }
        return null;
    }

    static UIView flow(UIComponent layout) {
        for (UIComponent component : ((UIContainer) layout).components) {
        }
        return null;
    }

    static org.robovm.apple.uikit.UIButton button(UIComponent layout) {
//        final UIButton button = (UIButton) layout;
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

    static org.robovm.apple.uikit.UILabel label(UIComponent layout) {
//        UILabel label = (UILabel) layout;
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
