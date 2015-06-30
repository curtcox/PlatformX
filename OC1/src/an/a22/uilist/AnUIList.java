package an.a22.uilist;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import x.Registry;
import x.event.Action;
import x.uilist.UIList;

final class AnUIList<T>
    extends ListView
    implements UIList
{
    private final AnFilterListModel model;

    private AnUIList(AnFilterListModel model) {
        super(context());
        this.model = model;
    }

    static AnUIList of(AnFilterListModel model) {
        return new AnUIList(model);
    }

    @Override
    public void addActionListener(final Action.Listener listener) {
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.actionPerformed(new Action(AnUIList.this));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    @Override
    public int getSelectedIndex() {
        return super.getSelectedItemPosition();
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}
