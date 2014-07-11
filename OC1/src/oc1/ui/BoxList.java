package oc1.ui;

import com.codename1.ui.Container;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;

/**
 *
 * @author Curt
 */
final class BoxList
    extends Container
    implements IList
{

    private final ListModel model;
    private final BoxLayout layout;
    
    BoxList(ListModel model, ListCellRenderer renderer) {
        this.model = model;
        layout = new BoxLayout(BoxLayout.Y_AXIS);
        for (int i=0; i<model.getSize(); i++) {
            this.addComponent(this);
        }
        this.setLayout(layout);
    }

    public void addActionListener(ActionListener listener) {
    }

    public int getSelectedIndex() {
        return 0;
    }
}
