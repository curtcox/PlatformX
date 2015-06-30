package c1.uilist;

import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;
import x.event.Action;

final class C1UIContainerList<T>
    extends ContainerList 
    implements UIList
{
    C1UIContainerList(ListModel model) {
        super(model);
    }

    public void setCellRenderer(C1BasicListCellRenderer renderer) {
        super.setRenderer(renderer);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }
}
