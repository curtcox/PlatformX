package ios.uiwidget;

import org.robovm.apple.uikit.*;
import x.uiwidget.XButton;

public final class IosButtonViewController
    extends UIViewController
{
    final XButton xButton;
    final UIButton uiButton;

    private IosButtonViewController(XButton xButton) {
        this.xButton = xButton;
        this.uiButton = newButton(xButton);
        setView(uiButton);
    }

    public static IosButtonViewController of(XButton button) {
        return new IosButtonViewController(button);
    }

    private static UIButton newButton(XButton xButton) {
        UIButton uiButton = UIButton.create(UIButtonType.System);
        uiButton.setTitle(xButton.getText(), UIControlState.Normal);
        uiButton.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));
        uiButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside(UIControl control, UIEvent event) {
                xButton.onTap();
            }
        });
        return uiButton;
    }

    public void setEnabled(boolean enabled) {
        uiButton.setEnabled(enabled);
    }

}
