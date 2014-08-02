package oc1.uilist;

import oc1.uilist.ListCell;

/**
 * For configuring a ListCell, in order to display a value.
 * @author Curt
 * @param <T> the kind of items in the list
 */
public interface ListCellConfigurer<T> {
    void configureButton(ListCell button, T value);
}
