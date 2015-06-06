package se.uilist;

import common.uilist.IListModel;
import common.uilist.ListCellConfigurer;
import common.uilist.UIList;

import javax.swing.*;

import static common.uilist.UIList.Factory;

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
