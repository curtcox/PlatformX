package an.a22.uilist;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import x.Registry;
import x.event.Action;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

final class AnUIList<T>
    extends ListView
    implements UIList
{
    private final ListAdapter model;

    private AnUIList(ListAdapter model) {
        super(context());
        this.model = model;
    }

    public static AnUIList of(ListAdapter model) {
        return new AnUIList(model);
    }

    public static UIList of(ListAdapter model,ListCellConfigurer configurer) {
        AnUIList list = AnUIList.of(model);
        list.setRenderer(new AnBasicListCellRenderer(configurer));
        return list;
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

    private static Context context() {
        return Registry.get(Context.class);
    }

}
