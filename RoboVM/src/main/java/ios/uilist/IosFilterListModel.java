package ios.uilist;

import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.foundation.NSObject;
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
    extends NSObject
    implements UITableViewDataSource
{
    private final LiveList filtered;
    private IosBasicListCellRenderer renderer;
    private final XListOffsets<T> offsets;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private IosFilterListModel(LiveList filtered, IosBasicListCellRenderer renderer) {
        System.out.println("filtered = " + filtered + " renderer = " + renderer);
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
        System.out.println("offsets = " + offsets.getSize());
        return offsets.getSize();
    }

    @Override
    public UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath) {
        return renderer.getListCellView(tableView, offsets.getElementAt(index(indexPath)));
    }

    private int index(NSIndexPath indexPath) {
        return (int) indexPath.getItem();
    }

    @Override
    public long getNumberOfSections(UITableView tableView) {
        System.out.println("getNumberOfSections = " + tableView);
        return 1;
    }

    @Override
    public String getTitleForHeader(UITableView tableView, long section) {
        return "";
    }

    @Override
    public String getTitleForFooter(UITableView tableView, long section) {
        return "";
    }

    @Override
    public boolean canEditRow(UITableView tableView, NSIndexPath indexPath) {
        return false;
    }

    @Override
    public boolean canMoveRow(UITableView tableView, NSIndexPath indexPath) {
        return false;
    }

    @Override
    public List<String> getSectionIndexTitles(UITableView tableView) {
        System.out.println("getSectionIndexTitles = " + tableView);
        return Collections.emptyList();
    }

    @Override
    public long getSectionForSectionIndexTitle(UITableView tableView, String title, long index) {
        System.out.println("getSectionForSectionIndexTitle = " + tableView);
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
