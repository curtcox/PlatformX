package ios.uilist;

import ios.uiwidget.IosBorderViewController;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import x.event.Action;
import x.event.LiveList;
import x.uilist.IXListCell;
import x.uiwidget.XSearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class IosSearchableList<T>
    implements XSearchableList
{

    final UILabel searchTerm = new UILabel();

    final IosFilterListModel<T> filterListModel;
    private final IosUIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final UIViewController component;

    private IosSearchableList(LiveList<T> items, UIView action, IXListCell.ConfigProducer configurer) {
        filterListModel = IosFilterListModel.of(items,new IosBasicListCellRenderer(configurer));
        filteredList = IosUIList.of(filterListModel,configurer);
        component = IosBorderViewController.of(filteredList.getView())
             .north(newNorthContainer(action));
    }

    public static IosSearchableList of(LiveList items, UIView action, IXListCell.ConfigProducer configurer) {
        return new IosSearchableList(items,action,configurer);
    }

    private UIViewController newNorthContainer(UIView action) {
        return IosBorderViewController.of(searchTerm).east(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public UIViewController getComponent() {
        return component;
    }

    public Object getSelected() {
        return filterListModel.getItem(filteredList.getSelectedIndex());
    }

}
