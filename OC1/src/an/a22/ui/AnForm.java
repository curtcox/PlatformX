package an.a22.ui;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import common.Registry;
import common.command.Command;
import common.page.ScreenLink;
import common.ui.IForm;
import common.uiwidget.UIComponent;

public final class AnForm
    extends LinearLayout
    implements IForm
{
    private final ScreenLink link;
    private Command back;
    private UIComponent layout;
    private Button backButton;

    AnForm(ScreenLink link) {
        super(context());
        this.link = link;
    }

    @Override
    public void layout(UIComponent layout) {
        removeAllViews();
        this.layout = layout;
        addView(AnUIRenderer.render(layout));
        show();
    }

    @Override
    public void show() {
        display().show(this);
    }

    @Override
    public void setBackCommand(Command back) {
        this.back = back;
        backButton.setEnabled(back!=null);
    }

    @Override
    public void showBack() {
        show();
    }

    @Override
    public String getTitle() {
        return link.title();
    }

    @Override
    public ScreenLink getScreenLink() {
        return link;
    }

    private AnDisplay display() {
        return AnDisplay.of();
    }

    private void displayUI() {
        TextView label = new TextView(context());
        label.setText("Hello World");
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout layout = new LinearLayout(context());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(label);

        activity().setContentView(layout);
    }

    private static Activity activity() {
        return Registry.get(Activity.class);
    }

    private static Context context() {
        return Registry.get(Context.class);
    }
}
