package ios.uiwidget;

import org.robovm.apple.uikit.*;
import x.uiwidget.XLabel;

public final class IosLabelViewController
    extends UIViewController
{
    final XLabel label;

    private IosLabelViewController(XLabel xLabel) {
        this.label = xLabel;
        setView(newLabel(xLabel));
    }

    public static IosLabelViewController of(XLabel label) {
        return new IosLabelViewController(label);
    }

    private static UILabel newLabel(XLabel xLabel) {
        UILabel uiLabel = new UILabel();
        uiLabel.setText(xLabel.text);
        uiLabel.setBackgroundColor(UIColor.brown());
        return uiLabel;
    }

    public String getText() {
        return label.text;
    }
}
