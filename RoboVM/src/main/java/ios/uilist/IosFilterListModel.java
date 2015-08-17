package ios.uilist;

import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellEditingStyle;
import org.robovm.apple.uikit.UITableViewDataSource;
import x.event.LiveList;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import java.util.Collections;
import java.util.List;

final class IosFilterListModel<T>
    implements UITableViewDataSource
{
    private final LiveList filtered;
    private IosBasicListCellRenderer renderer;
    private final XListOffsets<T> offsets;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private IosFilterListModel(LiveList filtered, IosBasicListCellRenderer renderer) {
        this.filtered = filtered;
        this.renderer = renderer;
        this.offsets = XListOffsets.of(filtered);
    }

    public static IosFilterListModel of(LiveList filtered,IosBasicListCellRenderer renderer) {
        IosFilterListModel model = new IosFilterListModel(filtered,renderer);
        return model;
    }

    public void setFilter(ListFilter filter) {
        offsets.setFilter(filter);
        dataChanged();
    }

    @Override
    public long getNumberOfRowsInSection(UITableView tableView, long section) {
        return offsets.getSize();
    }

    @Override
    public UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath) {
        return renderer.getListCellView(tableView,offsets.getElementAt(index(indexPath)));
    }

    private int index(NSIndexPath indexPath) {
        return indexPath.getItem();
    }

    @Override
    public long getNumberOfSections(UITableView uiTableView) {
        return 1;
    }

    @Override
    public String getTitleForHeader(UITableView uiTableView, long section) {
        return "";
    }

    @Override
    public String getTitleForFooter(UITableView uiTableView, long section) {
        return "";
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
        return Collections.emptyList();
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
        return (T) offsets.getElementAt(selectedIndex);
    }

    public void dataChanged() {

    }
}
