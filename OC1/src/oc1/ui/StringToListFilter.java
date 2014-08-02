package oc1.ui;

/**
 *
 * @author Curt
 */
public interface StringToListFilter {

    ListFilter listFilterFor(String text);
 
    StringToListFilter ALLOW_ALL = new StringToListFilter() {
        public ListFilter listFilterFor(String text) {
            return ListFilter.ALLOW_ALL;
        }
    };
}
