package se.uilist;

import se.uiwidget.SEBorderContainer;
import se.uiwidget.SEGhostText;
import x.event.Action;
import x.event.LiveList;
import x.uilist.IXListCell;
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
    private final SEUIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final JComponent component;

    private SESearchableList(LiveList<T> items, JComponent action, IXListCell.ConfigProducer configurer) {
        filterListModel = SEFilterListModel.of(items);
        filteredList = SEUIList.of(filterListModel, configurer);
        component = component(action);
        SEGhostText.installOn(searchTerm,"Search for ...");
    }

    private JComponent component(JComponent action) {
        return new SEBorderContainer(new JScrollPane(filteredList))
                .addNorth(newNorthContainer(action));
    }

    public static SESearchableList of(LiveList items, JComponent action, IXListCell.ConfigProducer configurer) {
        return new SESearchableList(items,action,configurer);
    }

    private JComponent newNorthContainer(JComponent action) {
        return new SEBorderContainer(searchTerm).addEast(action);
    }

    @Override
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    @Override
    public T getSelected() {
        return filterListModel.getElementAt(filteredList.getSelectedIndex());
    }

    @Override
    public String toString() {
        return "SESearchableList filterListModel=" + filterListModel;
    }
}
