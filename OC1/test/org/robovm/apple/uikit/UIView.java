package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGAffineTransform;
import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSCoder;
import org.robovm.apple.foundation.NSCoding;

public class UIView
        extends UIResponder
        implements NSCoding, UIAppearanceContainer, UIDynamicItem, UITraitEnvironment, UICoordinateSpace, UIAccessibilityIdentification
{
    private CGRect frame;

    public UIView() {
    }

    public UIView(CGRect frame) {
        this.frame = frame;
    }

    @Override
    public void encode(NSCoder nsCoder) {

    }

    @Override
    public String getAccessibilityIdentifier() {
        return null;
    }

    @Override
    public void setAccessibilityIdentifier(String s) {

    }

    @Override
    public CGPoint convertPointToCoordinateSpace(CGPoint cgPoint, UICoordinateSpace uiCoordinateSpace) {
        return null;
    }

    @Override
    public CGPoint convertPointFromCoordinateSpace(CGPoint cgPoint, UICoordinateSpace uiCoordinateSpace) {
        return null;
    }

    @Override
    public CGRect convertRectToCoordinateSpace(CGRect cgRect, UICoordinateSpace uiCoordinateSpace) {
        return null;
    }

    @Override
    public CGRect convertRectFromCoordinateSpace(CGRect cgRect, UICoordinateSpace uiCoordinateSpace) {
        return null;
    }

    @Override
    public CGPoint getCenter() {
        return null;
    }

    @Override
    public void setCenter(CGPoint cgPoint) {

    }

    @Override
    public CGRect getBounds() {
        return null;
    }

    @Override
    public CGAffineTransform getTransform() {
        return null;
    }

    @Override
    public void setTransform(CGAffineTransform cgAffineTransform) {

    }

    @Override
    public UITraitCollection getTraitCollection() {
        return null;
    }

    @Override
    public void traitCollectionDidChange(UITraitCollection uiTraitCollection) {

    }

    public void setFrame(CGRect frame) {
        this.frame = frame;
    }
}
