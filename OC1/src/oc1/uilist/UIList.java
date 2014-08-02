package oc1.uilist;

import oc1.uilist.IList;
import com.codename1.ui.List;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;

/**
 *
 * @author Curt
 */
final class UIList<T>
    extends List<T> 
    implements IList
{
    UIList(ListModel model) {
        super(model);
    }

    public void setCellRenderer(ListCellRenderer renderer) {
        super.setRenderer(renderer);
    }
}
