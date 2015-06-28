package ios.uilist;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import x.Registry;
import x.event.Action;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

/**
 * For displaying items from a ListModel.
 */
final class IosBoxList
    extends LinearLayout
    implements UIList
{
    private final ListAdapter model;
    private Action.Listener actionListener;
    private final ListCellConfigurer configurer;
    private int selectedIndex;

    IosBoxList(ListAdapter model, ListCellConfigurer configurer) {
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
