package se.uilist;

import se.ui.SEBorderContainer;
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
final class SESearchableList<T>
    implements XSearchableList<T>
{

    final JTextField searchTerm = new JTextField();
    final SEFilterListModel<T> filterListModel;
    private final SEUIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    final JComponent component;

    private SESearchableList(LiveList<T> items, JComponent action, IXListCell.ConfigProducer configurer) {
        filterListModel = SEFilterListModel.of(items);
        filteredList = SEUIList.of(filterListModel, configurer);
        component = component(action);
        SEGhostText.installOn(searchTerm,"Search for ...");
    }

    private JComponent component(JComponent action) {
        return SEBorderContainer.of(new JScrollPane(filteredList))
                .north(newNorthContainer(action));
    }

    public static SESearchableList of(LiveList items, JComponent action, IXListCell.ConfigProducer configurer) {
        return new SESearchableList(items,action,configurer);
    }

    private JComponent newNorthContainer(JComponent action) {
        return SEBorderContainer.of(searchTerm).east(action);
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
