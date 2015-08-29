package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.foundation.*;
import org.robovm.objc.Selector;

import java.util.List;

public class UITableViewController
        extends UIViewController
        implements UITableViewDelegate, UITableViewDataSource
{
    UITableView tableView = new UITableView();

    public UITableViewController() {}

    public UITableViewController(UITableViewStyle style) {
    }

    public UITableViewController(String nibNameOrNil, NSBundle nibBundleOrNil) {
    }

    public UITableViewController(NSCoder aDecoder) {
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

    @Override
    public long getNumberOfRowsInSection(UITableView uiTableView, long l) {
        return 0;
    }

    @Override
    public UITableViewCell getCellForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public long getNumberOfSections(UITableView uiTableView) {
        return 0;
    }

    @Override
    public String getTitleForHeader(UITableView uiTableView, long l) {
        return null;
    }

    @Override
    public String getTitleForFooter(UITableView uiTableView, long l) {
        return null;
    }

    @Override
    public boolean canEditRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public boolean canMoveRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public List<String> getSectionIndexTitles(UITableView uiTableView) {
        return null;
    }

    @Override
    public long getSectionForSectionIndexTitle(UITableView uiTableView, String s, long l) {
        return 0;
    }

    @Override
    public void commitEditingStyleForRow(UITableView uiTableView, UITableViewCellEditingStyle uiTableViewCellEditingStyle, NSIndexPath nsIndexPath) {

    }

    @Override
    public void moveRow(UITableView uiTableView, NSIndexPath nsIndexPath, NSIndexPath nsIndexPath1) {

    }

    @Override
    public void willDisplayCell(UITableView uiTableView, UITableViewCell uiTableViewCell, NSIndexPath nsIndexPath) {

    }

    @Override
    public void willDisplayHeaderView(UITableView uiTableView, UIView uiView, long l) {

    }

    @Override
    public void willDisplayFooterView(UITableView uiTableView, UIView uiView, long l) {

    }

    @Override
    public void didEndDisplayingCell(UITableView uiTableView, UITableViewCell uiTableViewCell, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didEndDisplayingHeaderView(UITableView uiTableView, UIView uiView, long l) {

    }

    @Override
    public void didEndDisplayingFooterView(UITableView uiTableView, UIView uiView, long l) {

    }

    @Override
    public double getHeightForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return 0;
    }

    @Override
    public double getHeightForHeader(UITableView uiTableView, long l) {
        return 0;
    }

    @Override
    public double getHeightForFooter(UITableView uiTableView, long l) {
        return 0;
    }

    @Override
    public double getEstimatedHeightForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return 0;
    }

    @Override
    public double getEstimatedHeightForHeader(UITableView uiTableView, long l) {
        return 0;
    }

    @Override
    public double getEstimatedHeightForFooter(UITableView uiTableView, long l) {
        return 0;
    }

    @Override
    public UIView getViewForHeader(UITableView uiTableView, long l) {
        return null;
    }

    @Override
    public UIView getViewForFooter(UITableView uiTableView, long l) {
        return null;
    }

    @Override
    public void accessoryButtonTapped(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public boolean shouldHighlightRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public void didHighlightRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didUnhighlightRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public NSIndexPath willSelectRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public NSIndexPath willDeselectRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public void didSelectRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didDeselectRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public UITableViewCellEditingStyle getEditingStyleForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public String getTitleForDeleteConfirmationButton(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public NSArray<UITableViewRowAction> getEditActionsForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return null;
    }

    @Override
    public boolean shouldIndentWhileEditingRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public void willBeginEditingRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public void didEndEditingRow(UITableView uiTableView, NSIndexPath nsIndexPath) {

    }

    @Override
    public NSIndexPath getTargetForMove(UITableView uiTableView, NSIndexPath nsIndexPath, NSIndexPath nsIndexPath1) {
        return null;
    }

    @Override
    public long getIndentationLevelForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return 0;
    }

    @Override
    public boolean shouldShowMenuForRow(UITableView uiTableView, NSIndexPath nsIndexPath) {
        return false;
    }

    @Override
    public boolean canPerformAction(UITableView uiTableView, Selector selector, NSIndexPath nsIndexPath, NSObject nsObject) {
        return false;
    }

    @Override
    public void performActionForRow(UITableView uiTableView, Selector selector, NSIndexPath nsIndexPath, NSObject nsObject) {

    }

    public UITableView getTableView() {
          return tableView;
    }
}