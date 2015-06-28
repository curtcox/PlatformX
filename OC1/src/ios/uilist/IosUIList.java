package ios.uilist;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import x.event.Action;
import x.uilist.UIList;

final class IosUIList<T>
    extends ListView
    implements UIList
{
    private IosUIList(Context context) {
        super(context);
    }

    public static IosUIList of(ListAdapter model) {
        return null;
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(IosBasicListCellRenderer renderer) {

    }
}
