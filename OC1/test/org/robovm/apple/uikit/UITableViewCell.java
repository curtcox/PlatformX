package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSCoding;

public class UITableViewCell
        extends UIView
        implements NSCoding, UIGestureRecognizerDelegate
{
    public UITableViewCell() {}

    public UITableViewCell(UITableViewCellStyle style, String reuseIdentifier) {
    }

    public UITableViewCell(CGRect frame) {
        super(frame);
    }

    @Override
    public boolean shouldBegin(UIGestureRecognizer uiGestureRecognizer) {
        return false;
    }

    @Override
    public boolean shouldRecognizeSimultaneously(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1) {
        return false;
    }

    @Override
    public boolean shouldRequireFailure(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1) {
        return false;
    }

    @Override
    public boolean shouldBeRequiredToFail(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1) {
        return false;
    }

    @Override
    public boolean shouldReceiveTouch(UIGestureRecognizer uiGestureRecognizer, UITouch uiTouch) {
        return false;
    }
}
