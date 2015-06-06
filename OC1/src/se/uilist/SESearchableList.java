package se.uilist;

import common.event.Action;
import common.event.LiveList;
import common.uilist.IListModel;
import common.uilist.ListCellConfigurer;
import common.uilist.UIList;
import common.uiwidget.ISearchableList;
import se.uiwidget.BorderContainer;

import javax.swing.*;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class SESearchableList<T>
    implements ISearchableList<T>
{

    final JTextField searchTerm = new JTextField();
    final SEFilterListModel<T> filterListModel;
    private final ListModel<T> underlyingListModel;
    private final UIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final JComponent component;

    private SESearchableList(UIList.Factory factory, LiveList<T> items, JComponent action, ListCellConfigurer configurer) {
        underlyingListModel = SEVirtualListModel.of(items);
        filterListModel = new SEFilterListModel(underlyingListModel);
        filteredList = factory.of(convert(filterListModel),configurer);
        component = new BorderContainer((JComponent)filteredList)
             .addNorth(newNorthContainer(action));
    }

    private IListModel convert(SEFilterListModel<T> filterListModel) {
        return new SEListModelAsIListModel(filterListModel);
    }

    public SESearchableList(LiveList<T> items, JComponent action, ListCellConfigurer configurer) {
        this(SEListFactories.BOX,items,action,configurer);
    }

    private JComponent newNorthContainer(JComponent action) {
        return new BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    public T getSelected() {
        return filterListModel.getElementAt(filteredList.getSelectedIndex());
    }

}
