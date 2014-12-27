package c1.uilist;

/**
 * For configuring a ListCell, in order to display a value.
 * @author Curt
 * @param <T> the kind of items in the list
 */
public interface ListCellConfigurer<T> {
    void configureButton(ListCell button, T value);
}
