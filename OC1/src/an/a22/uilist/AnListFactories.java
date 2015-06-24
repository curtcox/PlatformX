package an.a22.uilist;

import android.widget.ListAdapter;
import x.uilist.IListModel;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

import static x.uilist.UIList.Factory;

final class AnListFactories {

    static Factory LIST = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            AnUIList list = AnUIList.of(convert(model));
            list.setRenderer(new AnBasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            return new AnBoxList(convert(model),configurer);
        }
    };

    private static ListAdapter convert(IListModel model) {
        return new IListModelAsAnListModel(model);
    }

}
