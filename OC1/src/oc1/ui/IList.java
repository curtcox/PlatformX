package oc1.ui;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;

/**
 *
 * @author Curt
 */
interface IList {
    
    interface Factory {
        IList of(ListModel model,ListCellConfigurer configurer);
    }

    static Factory UI = new Factory() {
        public IList of(ListModel model,ListCellConfigurer configurer) {
            UIList list = new UIList(model);       
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
