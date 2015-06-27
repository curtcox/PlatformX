package an.a22.uilist;

import an.a22.uiwidget.AnBorderContainer;
import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import x.Registry;
import x.event.Action;
import x.event.LiveList;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;
import x.uiwidget.ISearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class AnSearchableList<T>
    implements ISearchableList
{

    final TextView searchTerm = new TextView(context());

    final AnFilterListModel<T> filterListModel;
    private final ListAdapter underlyingListModel;
    private final UIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final View component;

    private AnSearchableList(AnListFactories.Factory factory, LiveList<T> items, View action, ListCellConfigurer configurer) {
        underlyingListModel = AnVirtualListModel.of(items);
        filterListModel = AnFilterListModel.of(underlyingListModel);
        filteredList = factory.of(filterListModel,configurer);
        component = AnBorderContainer.of((View) filteredList)
             .addNorth(newNorthContainer(action));
    }

    public AnSearchableList(LiveList<T> items, View action, ListCellConfigurer configurer) {
        this(AnListFactories.BOX,items,action,configurer);
    }

    private View newNorthContainer(View action) {
        return AnBorderContainer.of(searchTerm).addEast(action);
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
