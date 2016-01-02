package org.robovm.apple.uikit;

public class UIControl
        extends UIView
{
    protected interface Listener {}

    public interface OnTouchUpInsideListener extends UIControl.Listener {
        void onTouchUpInside(UIControl var1, UIEvent var2);
    }

    public void addOnTouchUpInsideListener(UIControl.OnTouchUpInsideListener l) {
    }
}
