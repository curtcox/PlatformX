package common.uilist;

/**
 * For configuring a ListCell, in order to display a value.
 * @param <T> the kind of items in the list
 */
public interface ListCellConfigurer<T> {
    void configureButton(IListCell button, T value);
}
