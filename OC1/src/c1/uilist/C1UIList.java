package c1.uilist;

import com.codename1.ui.List;
import com.codename1.ui.list.ListModel;
import x.event.Action;

final class C1UIList<T>
    extends List<T> 
    implements UIList
{
    C1UIList(ListModel model) {
        super(model);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }
}
