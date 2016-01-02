package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSObject;

public class UIScreen extends NSObject implements UITraitEnvironment {

    public static UIScreen getMainScreen() {
        return new UIScreen();
    }

    public CGRect getBounds() {
        return new CGRect(0,0,0,0);
    }

    @Override
    public UITraitCollection getTraitCollection() {
        return null;
    }

    @Override
    public void traitCollectionDidChange(UITraitCollection uiTraitCollection) {

    }
}
