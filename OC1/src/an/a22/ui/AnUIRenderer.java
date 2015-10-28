package an.a22.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;
import x.util.Strings;

final class AnUIRenderer {

    static View render(XComponent layout) {
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

    private static View peer(XComponent layout) {
        XPeeredComponent peered = (XPeeredComponent) layout;
        View peer = (View) peered.peer;
        log(layout + ">" + peer);
        return peer;
    }

    static LinearLayout column(XComponent layout) {
        return box(layout,LinearLayout.VERTICAL);
    }

    static LinearLayout row(XComponent layout) {
        return box(layout,LinearLayout.HORIZONTAL);
    }

    static LinearLayout box(XComponent layout,int axis) {
        LinearLayout aLayout = new LinearLayout(context());
        aLayout.setOrientation(axis);
        aLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        for (XComponent component : ((XContainer) layout).components) {
            aLayout.addView(render(component));
        }
        log(layout + ">" + aLayout);
        return aLayout;
    }

    static LinearLayout flow(XComponent layout) {
        LinearLayout aLayout = new LinearLayout(context());
        aLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        for (XComponent component : ((XContainer) layout).components) {
            aLayout.addView(render(component));
        }
        log(layout + ">" + aLayout);
        return aLayout;
    }

    static Button button(XComponent layout) {
        final XButton button = (XButton) layout;
        Button aButton = new Button(context());
        aButton.setText(Strings.asChars(button.text));
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.onTap();
            }
        });
        log(button + ">" + aButton);
        return aButton;
    }

    static TextView label(XComponent layout) {
        XLabel label = (XLabel) layout;
        TextView aLabel = new TextView(context());
        aLabel.setText(Strings.asChars(label.text));
        log(label + ">" + aLabel);
        return aLabel;
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(null,AnUIRenderer.class);
    }

}
