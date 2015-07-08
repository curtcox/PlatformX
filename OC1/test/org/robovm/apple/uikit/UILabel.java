package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSCoding;

public class UILabel extends UIView implements NSCoding {

    private String text;
    private UIFont font;

    public UILabel() {}

    public UILabel(CGRect frame) {
        super(frame);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public  UIFont getFont() {
        return font;
    }

    public  void setFont(UIFont font) {
        this.font = font;
    }
}
