package va.uilist;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import va.uiwidget.VaBorderContainer;
import x.event.Action;
import x.event.LiveList;
import x.uilist.IXListCell;
import x.uiwidget.XSearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class VaSearchableList<T>
    implements XSearchableList
{

    final TextField searchTerm = new TextField();

    final VaFilterListModel<T> filterListModel;
    private final VaUIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final Component component;

    private VaSearchableList(LiveList<T> items, Component action, IXListCell.ConfigProducer configurer) {
        filterListModel = VaFilterListModel.of(items);
        filteredList = VaUIList.of(filterListModel,configurer);
        component = VaBorderContainer.of(filteredList)
             .addNorth(newNorthContainer(action));
    }

    public static VaSearchableList of(LiveList items, Component action, IXListCell.ConfigProducer configurer) {
        return new VaSearchableList(items,action,configurer);
    }

    private Component newNorthContainer(Component action) {
        return VaBorderContainer.of(searchTerm).addEast(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public Component getComponent() {
        return component;
    }

    public Object getSelected() {
        return filterListModel.getItem(filteredList.getSelectedIndex());
    }

}
