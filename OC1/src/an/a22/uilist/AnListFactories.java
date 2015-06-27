package an.a22.uilist;

import android.widget.ListAdapter;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

final class AnListFactories {

    interface Factory {
        UIList of(ListAdapter model,ListCellConfigurer configurer);
    }

    static Factory LIST = new Factory() {
        public UIList of(ListAdapter model,ListCellConfigurer configurer) {
            AnUIList list = AnUIList.of(model);
            list.setRenderer(new AnBasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(ListAdapter model,ListCellConfigurer configurer) {
            return new AnBoxList(model,configurer);
        }
    };

}
