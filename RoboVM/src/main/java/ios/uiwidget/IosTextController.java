package ios.uiwidget;

import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegate;
import org.robovm.apple.uikit.UIViewController;

public final class IosTextController
    extends UIViewController
{

    UITextField textField;

    private IosTextController() {
        textField = newTextView();
        setView(textField);
    }

    public static IosTextController of() {
        return new IosTextController();
    }

    private static UITextField newTextView() {
        UITextField textField = new UITextField();
        return textField;
    }

    public void setPlaceholder(String text) {
        textField.setPlaceholder(text);
    }

    public void setDelegate(UITextFieldDelegate delegate) {
        textField.setDelegate(delegate);
    }

    public String getText() {
        return textField.getText();
    }
}
