package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.Selector;
import org.robovm.rt.bro.annotation.ByVal;

public class UICollectionViewController
        extends UIViewController
        implements UICollectionViewDelegate, UICollectionViewDataSource
{
    public UICollectionViewController() {
    }

    @Override
    public long getNumberOfItemsInSection(UICollectionView uiCollectionView, long l) {
        return 0;
    }

    @Override
    public UICollectionViewCell getCellForItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public long getNumberOfSections(UICollectionView uiCollectionView) {
        return 0;
    }

    @Override
    public UICollectionReusableView getViewForSupplementaryElement(UICollectionView uiCollectionView, String s, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public boolean canMoveItemAt(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public void moveItemAt(UICollectionView uiCollectionView, NSIndexPath nsIndexPath, NSIndexPath nsIndexPath1) {

    }

    @Override
    public boolean shouldHighlightItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public void didHighlightItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didUnhighlightItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {

    }

    @Override
    public boolean shouldSelectItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public boolean shouldDeselectItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public void didSelectItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didDeselectItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void willDisplayCell(UICollectionView uiCollectionView, UICollectionViewCell uiCollectionViewCell, NSIndexPath nsIndexPath) {

    }

    @Override
    public void willDisplaySupplementaryView(UICollectionView uiCollectionView, UICollectionReusableView uiCollectionReusableView, String s, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didEndDisplayingCell(UICollectionView uiCollectionView, UICollectionViewCell uiCollectionViewCell, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didEndDisplayingSupplementaryView(UICollectionView uiCollectionView, UICollectionReusableView uiCollectionReusableView, String s, NSIndexPath nsIndexPath) {

    }

    @Override
    public boolean shouldShowMenuForItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public boolean canPerformAction(UICollectionView uiCollectionView, Selector selector, NSIndexPath nsIndexPath, NSObject nsObject) {
        return false;
    }

    @Override
    public void performAction(UICollectionView uiCollectionView, Selector selector, NSIndexPath nsIndexPath, NSObject nsObject) {

    }

    @Override
    public UICollectionViewTransitionLayout getTransitionLayout(UICollectionView uiCollectionView, UICollectionViewLayout uiCollectionViewLayout, UICollectionViewLayout uiCollectionViewLayout1) {
        return null;
    }

    @Override
    public boolean canFocusItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public boolean shouldUpdateFocus(UICollectionView uiCollectionView, UICollectionViewFocusUpdateContext uiCollectionViewFocusUpdateContext) {
        return false;
    }

    @Override
    public void didUpdateFocus(UICollectionView uiCollectionView, UICollectionViewFocusUpdateContext uiCollectionViewFocusUpdateContext, UIFocusAnimationCoordinator uiFocusAnimationCoordinator) {

    }

    @Override
    public NSIndexPath getIndexPathForPreferredFocusedView(UICollectionView uiCollectionView) {
        return null;
    }

    @Override
    public NSIndexPath getTargetIndexPathForMoveFromItem(UICollectionView uiCollectionView, NSIndexPath nsIndexPath, NSIndexPath nsIndexPath1) {
        return null;
    }

    @Override
    public CGPoint getTargetContentOffsetForProposedContentOffset(UICollectionView uiCollectionView, @ByVal CGPoint cgPoint) {
        return null;
    }

    @Override
    public void didScroll(UIScrollView uiScrollView) {

    }

    @Override
    public void didZoom(UIScrollView uiScrollView) {

    }

    @Override
    public void willBeginDragging(UIScrollView uiScrollView) {

    }

    @Override
    public void willEndDragging(UIScrollView uiScrollView, CGPoint cgPoint, CGPoint cgPoint1) {

    }

    @Override
    public void didEndDragging(UIScrollView uiScrollView, boolean b) {

    }

    @Override
    public void willBeginDecelerating(UIScrollView uiScrollView) {

    }

    @Override
    public void didEndDecelerating(UIScrollView uiScrollView) {

    }

    @Override
    public void didEndScrollingAnimation(UIScrollView uiScrollView) {

    }

    @Override
    public UIView getViewForZooming(UIScrollView uiScrollView) {
        return null;
    }

    @Override
    public void willBeginZooming(UIScrollView uiScrollView, UIView uiView) {

    }

    @Override
    public void didEndZooming(UIScrollView uiScrollView, UIView uiView, double v) {

    }

    @Override
    public boolean shouldScrollToTop(UIScrollView uiScrollView) {
        return false;
    }

    @Override
    public void didScrollToTop(UIScrollView uiScrollView) {

    }
}