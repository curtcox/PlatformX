package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSObject;

public class UIResponder
        extends NSObject
        implements UIAccessibility
{
    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    @Override
    public void setAccessibilityElement(boolean b) {

    }

    @Override
    public String getAccessibilityLabel() {
        return null;
    }

    @Override
    public void setAccessibilityLabel(String s) {

    }

    @Override
    public String getAccessibilityHint() {
        return null;
    }

    @Override
    public void setAccessibilityHint(String s) {

    }

    @Override
    public String getAccessibilityValue() {
        return null;
    }

    @Override
    public void setAccessibilityValue(String s) {

    }

    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        return null;
    }

    @Override
    public void setAccessibilityTraits(UIAccessibilityTraits uiAccessibilityTraits) {

    }

    @Override
    public CGRect getAccessibilityFrame() {
        return null;
    }

    @Override
    public void setAccessibilityFrame(CGRect cgRect) {

    }

    @Override
    public UIBezierPath getAccessibilityPath() {
        return null;
    }

    @Override
    public void setAccessibilityPath(UIBezierPath uiBezierPath) {

    }

    @Override
    public CGPoint getAccessibilityActivationPoint() {
        return null;
    }

    @Override
    public void setAccessibilityActivationPoint(CGPoint cgPoint) {

    }

    @Override
    public String getAccessibilityLanguage() {
        return null;
    }

    @Override
    public void setAccessibilityLanguage(String s) {

    }

    @Override
    public boolean areAccessibilityElementsHidden() {
        return false;
    }

    @Override
    public void setAccessibilityElementsHidden(boolean b) {

    }

    @Override
    public boolean isAccessibilityViewModal() {
        return false;
    }

    @Override
    public void setAccessibilityViewModal(boolean b) {

    }

    @Override
    public boolean shouldGroupAccessibilityChildren() {
        return false;
    }

    @Override
    public void setShouldGroupAccessibilityChildren(boolean b) {

    }

    @Override
    public UIAccessibilityNavigationStyle getAccessibilityNavigationStyle() {
        return null;
    }

    @Override
    public void setAccessibilityNavigationStyle(UIAccessibilityNavigationStyle uiAccessibilityNavigationStyle) {

    }
}
