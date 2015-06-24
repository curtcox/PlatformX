package an.a22.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.Registry;
import x.command.Command;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.UIComponent;

public final class AnForm
    extends LinearLayout
    implements IForm
{
    private final PageLink link;
    private Command back;
    private Button backButton;

    AnForm(PageLink link) {
        super(context());
        this.link = link;
        configureLayout();
        this.backButton = backButton();
    }

    @Override
    public void layout(UIComponent layout) {
        removeAllViews();
        addView(titleLabel());
        addView(backButton);
        addView(AnUIRenderer.render(layout));
        show();
    }

    private void configureLayout() {
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        setGravity(Gravity.CENTER);
    }

    private TextView titleLabel() {
        TextView label = new TextView(context());
        label.setText(link.title());
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER_HORIZONTAL);
        return label;
    }

    private Button backButton() {
        Button button = new Button(context());
        button.setText("<");
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                back.go();
                return true;
            }
        });
        return button;
    }

    @Override
    public void show() {
        display().show(this);
    }

    @Override
    public void setBackCommand(Command back) {
        this.back = back;
        backButton.setEnabled(back != null);
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
    public PageLink getScreenLink() {
        return link;
    }

    private AnDisplay display() {
        return AnDisplay.of();
    }

    private static Context context() {
        return Registry.get(Context.class);
    }
}
