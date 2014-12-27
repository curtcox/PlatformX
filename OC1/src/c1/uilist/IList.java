package c1.uilist;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.ListModel;

/**
 * For displaying a list of items.  Use a factory to create one.
 * @author Curt
 */
interface IList {
    
    interface Factory {
        IList of(ListModel model,ListCellConfigurer configurer);
    }

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

    void addActionListener(ActionListener listener);
    int getSelectedIndex();
}
