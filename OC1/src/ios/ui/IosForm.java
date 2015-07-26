package ios.ui;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.*;
import x.command.Command;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;

public final class IosForm
        extends UIViewController
     implements IForm
{
    private final PageLink link;
    private Command back;
    private UIButton backButton;

    IosForm(PageLink link) {
        this.link = link;
        this.backButton = backButton();
    }

    @Override
    public void layout(XComponent layout) {
        removeAllViews();
        addView(titleLabel());
        addView(backButton);
        addView(IosUIRenderer.render(layout));
        show();
    }

    private void removeAllViews() {
    }

    private void addView(UIView view) {
        getView().addSubview(view);
    }

    private UILabel titleLabel() {
        UILabel label = new UILabel(new CGRect(20, 250, 280, 44));
        label.setText(link.title());
        return label;
    }

    private UIButton backButton() {
        UIButton button = UIButton.create(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(110, 150, 100, 40));
        button.setTitle("<", UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));

        button.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside (UIControl control, UIEvent event) {
                back.go();
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

    private IosDisplay display() {
        return IosDisplay.of();
    }

}
