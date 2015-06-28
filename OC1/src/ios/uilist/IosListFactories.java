package ios.uilist;

import android.widget.ListAdapter;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

final class IosListFactories {

    interface Factory {
        UIList of(ListAdapter model, ListCellConfigurer configurer);
    }

    static Factory LIST = new Factory() {
        public UIList of(ListAdapter model,ListCellConfigurer configurer) {
            IosUIList list = IosUIList.of(model);
            list.setRenderer(new IosBasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(ListAdapter model,ListCellConfigurer configurer) {
            return new IosBoxList(model,configurer);
        }
    };

}
