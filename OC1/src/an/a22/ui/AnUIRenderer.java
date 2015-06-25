package an.a22.ui;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

final class AnUIRenderer {

    static View render(UIComponent layout) {
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

    private static View peer(UIComponent layout) {
        UIPeeredComponent peered = (UIPeeredComponent) layout;
        return (View) peered.peer;
    }

    static LinearLayout column(UIComponent layout) {
        return box(layout,LinearLayout.VERTICAL);
    }

    static LinearLayout row(UIComponent layout) {
        return box(layout,LinearLayout.HORIZONTAL);
    }

    static LinearLayout box(UIComponent layout,int axis) {
        LinearLayout aLayout = new LinearLayout(context());
        aLayout.setOrientation(axis);
        aLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        for (UIComponent component : ((UIContainer) layout).components) {
            aLayout.addView(render(component));
        }
        return aLayout;
    }

    static LinearLayout flow(UIComponent layout) {
        LinearLayout aLayout = new LinearLayout(context());
        aLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        for (UIComponent component : ((UIContainer) layout).components) {
            aLayout.addView(render(component));
        }
        return aLayout;
    }

    static Button button(UIComponent layout) {
        final UIButton button = (UIButton) layout;
        Button aButton = new Button(context());
        aButton.setText(button.text);
        aButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                button.onTap();
                return true;
            }
        });
        return aButton;
    }

    static TextView label(UIComponent layout) {
        UILabel label = (UILabel) layout;
        TextView aLabel = new TextView(context());
        aLabel.setText(label.text);
        return aLabel;
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(AnUIRenderer.class);
    }

}
