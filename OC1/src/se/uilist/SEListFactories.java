package se.uilist;

import x.uilist.IListModel;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

import javax.swing.*;

import static x.uilist.UIList.Factory;

final class SEListFactories {

    static Factory LIST = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            SEUIList list = new SEUIList(convert(model));
            list.setRenderer(new SEBasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            return new SEBoxList(convert(model),configurer);
        }
    };

    private static ListModel convert(IListModel model) {
        return new IListModelAsSEListModel(model);
    }

}
