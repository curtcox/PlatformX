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
            /**
             * <p>Callback method to be invoked when an item in this view has been
             * selected. This callback is invoked only when the newly selected
             * position is different from the previously selected position or if
             * there was no selected item.</p>
             *
             * Impelmenters can call getItemAtPosition(position) if they need to access the
             * data associated with the selected item.
             *
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view!=null) {
                    listener.actionPerformed(new Action(AnUIList.this));
                }
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
