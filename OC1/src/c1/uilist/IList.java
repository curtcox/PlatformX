package c1.uilist;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.ListModel;

/**
 * For displaying a list of items.  Use a factory to create one.
 */
interface IList {

    interface Factory {
        IList of(ListModel model,ListCellConfigurer configurer);
    }

    void addActionListener(ActionListener listener);
    int getSelectedIndex();
}
