package an.a22.uilist;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import common.Registry;
import common.event.Action;
import common.uilist.ListCellConfigurer;
import common.uilist.UIList;

/**
 * For displaying items from a ListModel.
 */
final class AnBoxList
    extends LinearLayout
    implements UIList
{
    private final ListAdapter model;
    private Action.Listener actionListener;
    private final ListCellConfigurer configurer;
    private int selectedIndex;

    AnBoxList(ListAdapter model, ListCellConfigurer configurer) {
        super(context());
        this.model = model;
        this.configurer = configurer;
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }
}
