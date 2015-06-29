package ios.uilist;

import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellEditingStyle;
import org.robovm.apple.uikit.UITableViewDataSource;
import x.event.LiveList;
import x.uilist.ListFilter;

import java.util.List;

final class IosFilterListModel<T>
    implements UITableViewDataSource
{
    private final LiveList filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private IosFilterListModel(LiveList filtered) {
        this.filtered = filtered;
    }

    public static IosFilterListModel of(LiveList filtered) {
        IosFilterListModel model = new IosFilterListModel(filtered);
        return model;
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
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

    public T getItem(int selectedIndex) {
        return null;
    }

    public void dataChanged() {

    }
}
