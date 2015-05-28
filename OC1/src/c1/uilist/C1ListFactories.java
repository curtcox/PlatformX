package c1.uilist;

import static c1.uilist.IList.Factory;
import com.codename1.ui.list.ListModel;

final class C1ListFactories {

    static Factory LIST = new Factory() {
        public IList of(ListModel model,ListCellConfigurer configurer) {
            UIList list = new UIList(model);
            list.setRenderer(new BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory CONTAINER = new Factory() {
        public IList of(ListModel model,ListCellConfigurer configurer) {
            UIContainerList list = new UIContainerList(model);
            list.setRenderer(new BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public IList of(ListModel model,ListCellConfigurer configurer) {
            return new BoxList(model,configurer);
        }
    };

}
