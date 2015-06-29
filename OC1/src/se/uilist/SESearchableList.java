package se.uilist;

import se.uiwidget.SEBorderContainer;
import x.event.Action;
import x.event.LiveList;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;
import x.uiwidget.XSearchableList;

import javax.swing.*;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class SESearchableList<T>
    implements XSearchableList<T>
{

    final JTextField searchTerm = new JTextField();
    final SEFilterListModel<T> filterListModel;
    private final UIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final JComponent component;

    private SESearchableList(LiveList<T> items, JComponent action, ListCellConfigurer configurer) {
        filterListModel = SEFilterListModel.of(items);
        filteredList = SEUIList.of(filterListModel, configurer);
        component = component(action);
    }

    private JComponent component(JComponent action) {
        return new SEBorderContainer(new JScrollPane((JComponent)filteredList))
                .addNorth(newNorthContainer(action));
    }

    public static SESearchableList of(LiveList items, JComponent action, ListCellConfigurer configurer) {
        return new SESearchableList(items,action,configurer);
    }

    private JComponent newNorthContainer(JComponent action) {
        return new SEBorderContainer(searchTerm).addEast(action);
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
