package an.a22.uilist;

import an.a22.uiwidget.AnBorderContainer;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import x.Registry;
import x.event.Action;
import x.event.LiveList;
import x.uilist.IXListCell;
import x.uiwidget.XSearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class AnSearchableList<T>
    implements XSearchableList
{

    final TextView searchTerm = new TextView(context());

    final AnFilterListModel<T> filterListModel;
    private final AnUIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final View component;

    private AnSearchableList(LiveList<T> items, View action, IXListCell.ConfigProducer configurer) {
        filterListModel = AnFilterListModel.of(items,configurer);
        filteredList = AnUIList.of(filterListModel);
        component = component(action);
    }

    private View component(View action) {
        return AnBorderContainer.of(filteredList)
                .north(newNorthContainer(action))
                .layout();
    }

    public static AnSearchableList of(LiveList items, View action, IXListCell.ConfigProducer configurer) {
        return new AnSearchableList(items,action,configurer);
    }

    private View newNorthContainer(View action) {
        return AnBorderContainer.of(searchTerm).east(action).layout();
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public View getComponent() {
        return component;
    }

    public Object getSelected() {
        return filterListModel.getItem(filteredList.getSelectedIndex());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}
