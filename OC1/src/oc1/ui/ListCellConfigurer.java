package oc1.ui;

/**
 *
 * @author Curt
 */
public interface ListCellConfigurer<T> {
    void configureButton(ListCell button, T value);
}
