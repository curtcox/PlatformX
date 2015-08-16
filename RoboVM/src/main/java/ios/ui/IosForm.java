package ios.ui;

import ios.uiwidget.IosBorderContainer;
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
        setView(renderedForm(layout));
        show();
    }

    private UIView renderedForm(XComponent layout) {
        return center(render(layout))
                .north(navigationPanel());
    }

    private UIView render(XComponent layout) {
        return IosUIRenderer.render(layout);
    }

    private UIView navigationPanel() {
        backButton = backButton();
        return center(address())
                .west(backButton);
    }

    private IosBorderContainer center(UIView center) {
        return IosBorderContainer.of(center);
    }

    private UILabel address() {
        UILabel label = new UILabel();
        label.setText(link.title());
        return label;
    }

    private UIButton backButton() {
        UIButton button = UIButton.create(UIButtonType.RoundedRect);
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
        getView().setNeedsDisplay();
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
