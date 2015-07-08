package org.robovm.apple.uikit;

import org.robovm.apple.foundation.NSCoding;

public class UIButton
        extends UIControl
        implements NSCoding
{
    private UILabel titleLabel;

    public static UIButton create(UIButtonType var0) {
        return new UIButton();
    }

    public void setTitle(String var1, UIControlState var2) {

    }

    public UILabel getTitleLabel() {
        return titleLabel;
    }
}
