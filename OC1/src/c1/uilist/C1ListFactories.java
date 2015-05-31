package c1.uilist;

import com.codename1.ui.list.ListModel;
import common.uilist.IListModel;
import common.uilist.ListCellConfigurer;
import common.uilist.UIList;

import static common.uilist.UIList.Factory;

final class C1ListFactories {

    static Factory LIST = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            C1UIList list = new C1UIList(convert(model));
            list.setRenderer(new BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory CONTAINER = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            UIContainerList list = new UIContainerList(convert(model));
            list.setRenderer(new BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            return new BoxList(convert(model),configurer);
        }
    };

    private static ListModel convert(IListModel model) {
        return new IListModelAsListModel(model);
    }

}
