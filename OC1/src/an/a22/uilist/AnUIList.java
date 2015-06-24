package an.a22.uilist;

import android.content.Context;
import android.widget.*;
import x.event.Action;
import x.uilist.UIList;

final class AnUIList<T>
    extends ListView
    implements UIList
{
    private AnUIList(Context context) {
        super(context);
    }

    public static AnUIList of(ListAdapter model) {
        return null;
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(AnBasicListCellRenderer renderer) {

    }
}
