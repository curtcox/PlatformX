package oc1.uilist;

import oc1.uilist.ListFilter;

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
