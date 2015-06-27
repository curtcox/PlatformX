package se.uilist;

import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

import javax.swing.*;

/**
 * A UIList.Factory for both of the list types we support.
 */
final class SEListFactories {

    interface Factory {
        UIList of(ListModel model,ListCellConfigurer configurer);
    }

    static Factory LIST = new Factory() {
        public UIList of(ListModel model,ListCellConfigurer configurer) {
            SEUIList list = new SEUIList(model);
            list.setRenderer(new SEBasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(ListModel model,ListCellConfigurer configurer) {
            return new SEBoxList(model,configurer);
        }
    };

}
