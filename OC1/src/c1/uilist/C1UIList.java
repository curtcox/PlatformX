package c1.uilist;

import com.codename1.ui.List;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import x.event.Action;
import x.uilist.UIList;

final class C1UIList<T>
    extends List<T> 
    implements UIList
{
    C1UIList(ListModel model) {
        super(model);
    }

    public void setCellRenderer(ListCellRenderer renderer) {
        super.setRenderer(renderer);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }
}
