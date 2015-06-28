package ios.uilist;

import ios.uiwidget.IosBorderContainer;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import x.event.Action;
import x.event.LiveList;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;
import x.uiwidget.XSearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class IosSearchableList<T>
    implements XSearchableList
{

    final UILabel searchTerm = new UILabel(new CGRect(20, 250, 280, 44));

    final IosFilterListModel<T> filterListModel;
    private final UIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final UIView component;

    private IosSearchableList(IosListFactories.Factory factory, LiveList<T> items, UIView action, ListCellConfigurer configurer) {
        filterListModel = IosFilterListModel.of(items);
        filteredList = factory.of(filterListModel,configurer);
        component = IosBorderContainer.of((UIView) filteredList)
             .addNorth(newNorthContainer(action));
    }

    public IosSearchableList(LiveList<T> items, UIView action, ListCellConfigurer configurer) {
        this(IosListFactories.BOX,items,action,configurer);
    }

    private UIView newNorthContainer(UIView action) {
        return IosBorderContainer.of(searchTerm).addEast(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public UIView getComponent() {
        return component;
    }

    public Object getSelected() {
        return filterListModel.getItem(filteredList.getSelectedIndex());
    }

}
