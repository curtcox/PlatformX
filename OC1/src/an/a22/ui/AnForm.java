package an.a22.ui;

import an.a22.uiwidget.AnBorderContainer;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.app.Registry;
import x.command.XCommand;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import x.util.Strings;

import static android.view.ViewGroup.LayoutParams.*;

public final class AnForm
    extends LinearLayout
    implements IForm
{
    final PageLink link;
    private XCommand back;
    private Button backButton;
    TextView address;

    AnForm(PageLink link) {
        super(context());
        this.link = link;
        configureLayout();
    }

    @Override
    public void layout(XComponent layout) {
        removeAllViews();
        addView(renderedForm(layout));
        show();
    }

    private View renderedForm(XComponent layout) {
        return center(render(layout))
                .north(navigationPanel())
                .layout();
    }

    private View render(XComponent layout) {
        return AnUIRenderer.render(layout);
    }

    private View navigationPanel() {
        backButton = backButton();
        return center(address())
                .west(backButton)
                .layout();
    }

    private AnBorderContainer center(View center) {
        return AnBorderContainer.of(center);
    }

    private void configureLayout() {
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(stretchXY());
        setGravity(Gravity.CENTER);
    }

    private static LayoutParams stretchXY() {
        return new LayoutParams(MATCH_PARENT, MATCH_PARENT);
    }

    private TextView address() {
        address = new TextView(context());
        address.setText(Strings.elided(link.title()));
        address.setTextSize(20);
        address.setGravity(Gravity.CENTER_HORIZONTAL);
        return address;
    }

    private Button backButton() {
        Button button = new Button(context());
        button.setText("<");
        button.setEnabled(false);
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
    public void setBackCommand(XCommand back) {
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
