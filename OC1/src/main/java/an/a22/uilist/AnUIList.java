package an.a22.uilist;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import x.app.Registry;
import x.event.Action;

final class AnUIList<T>
        extends ListView
{
    private int selected;

    private AnUIList() {
        super(context());
    }

    static AnUIList of(AnFilterListModel model) {
        AnUIList list =  new AnUIList();
        list.setAdapter(model);
        return list;
    }

    public void addActionListener(final Action.Listener listener) {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                listener.actionPerformed(new Action(AnUIList.this));
            }
        });
    }

    public int getSelectedIndex() {
        return selected;
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}
