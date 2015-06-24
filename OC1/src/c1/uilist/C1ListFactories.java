package c1.uilist;

import com.codename1.ui.list.ListModel;
import x.uilist.IListModel;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

import static x.uilist.UIList.Factory;

/**
 * Some factories for producing UI lists.
 * Multiple UI list implementations are an effort to understand (through trial and error) mysterious UI
 * freezes viewing lists, which have so far only been seen on Mike's Samsung phone.
 *
 * The first two are wrappers for CodenameOne UI lists.
 * http://codenameone.blogspot.com/2011/04/contain-that-list-bringing-containers.html
 *
 * The last one is mine.
 */
final class C1ListFactories {

    static Factory LIST = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            C1UIList list = new C1UIList(convert(model));
            list.setRenderer(new C1BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory CONTAINER = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            C1UIContainerList list = new C1UIContainerList(convert(model));
            list.setRenderer(new C1BasicListCellRenderer(configurer));
            return list;
        }
    };

    static Factory BOX = new Factory() {
        public UIList of(IListModel model,ListCellConfigurer configurer) {
            return new C1BoxList(convert(model),configurer);
        }
    };

    private static ListModel convert(IListModel model) {
        return new IListModelAsC1ListModel(model);
    }

}
