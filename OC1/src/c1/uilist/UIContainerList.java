package c1.uilist;

import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;
import common.event.Action;
import common.uilist.UIList;

final class UIContainerList<T>
    extends ContainerList 
    implements UIList
{
    UIContainerList(ListModel model) {
        super(model);
    }

    public void setCellRenderer(BasicListCellRenderer renderer) {
        super.setRenderer(renderer);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }
}
