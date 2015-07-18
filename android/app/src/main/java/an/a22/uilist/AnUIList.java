package an.a22.uilist;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import x.Registry;
import x.event.Action;

final class AnUIList<T>
    extends ListView
{
    private AnUIList() {
        super(context());
    }

    static AnUIList of(AnFilterListModel model) {
        AnUIList list =  new AnUIList();
        list.setAdapter(model);
        return list;
    }

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

    public int getSelectedIndex() {
        return super.getSelectedItemPosition();
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}
