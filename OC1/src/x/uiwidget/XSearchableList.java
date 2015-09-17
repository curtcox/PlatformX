package x.uiwidget;

import x.event.Action;
import x.event.LiveList;
import x.uilist.IXListCell;

/**
 * A graphical list of items that can be searched.
 * This interface is platform independent, but implementations are platform-specific.
 * (SESearchableList, AnSearchableList, etc...)
 * Implementations generally aren't platform-specific widgets, but provide access
 * to a platform-specific widget via getComponent().
 * @param <T> The item this is a list of.
 */
public interface XSearchableList<T> {

    /**
     * For getting a builder.
     */
    interface Factory {
        Builder builder();
    }

    /**
     * Something that can create an XSearchableList.
     */
    interface Builder {
        /**
         * @param items in the list
         */
        Builder items(LiveList items);

        /**
         * @param action an arbitrary component to be displayed at the top of the list.
         *               The motivating example is a zoom-out/broaden-search control.
         */
        Builder action(XComponent action);

        /**
         * @param configurer produces a cell configuration from an item
         */
        Builder configurer(IXListCell.ConfigProducer configurer);

        /**
         * Return a new XSearchableList.
         */
        XSearchableList build();
    }

    /**
     * Used to register the action to take on item selection.
     */
    void onSelected(Action.Listener actionListener);

    /**
     * Return the platform-specific component, for embedding in a Screen.
     * (JComponent, View, etc...)
     */
    Object getComponent();

    /**
     * Return the selected list item.
     */
    T getSelected();
}
