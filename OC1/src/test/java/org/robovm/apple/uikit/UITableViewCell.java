package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSCoding;

public class UITableViewCell
        extends UIView
        implements NSCoding, UIGestureRecognizerDelegate
{
    UILabel textLabel = new UILabel();

    public UITableViewCell() {}

    public UITableViewCell(UITableViewCellStyle style, String reuseIdentifier) {
    }

    public UITableViewCell(CGRect frame) {
        super(frame);
    }

    public UILabel getTextLabel() {
        return textLabel;
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

    @Override
    public boolean shouldReceivePress(UIGestureRecognizer uiGestureRecognizer, UIPress uiPress) {
        return false;
    }

    @Override
    public UIDynamicItemCollisionBoundsType getCollisionBoundsType() {
        return null;
    }

    @Override
    public UIBezierPath getCollisionBoundingPath() {
        return null;
    }
}
