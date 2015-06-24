package c1.uilist;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.list.ListModel;
import c1.uiwidget.C1BorderContainer;
import x.event.LiveList;
import x.event.Action;
import x.uilist.IListModel;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;
import x.uiwidget.ISearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class C1SearchableList<T>
    implements ISearchableList<T>
{

    final TextField searchTerm = new TextField();
    final C1FilterListModel<T> filterListModel;
    private final ListModel<T> underlyingListModel;
    private final UIList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final Component component;

    private C1SearchableList(UIList.Factory factory, LiveList<T> items, Component action, ListCellConfigurer configurer) {
        underlyingListModel = C1VirtualListModel.of(items);
        filterListModel = C1FilterListModel.of(underlyingListModel);
        filteredList = factory.of(convert(filterListModel),configurer);
        component = new C1BorderContainer((Component)filteredList)
             .addNorth(newNorthContainer(action));
    }

    private IListModel convert(C1FilterListModel<T> filterListModel) {
        return new C1ListModelAsIListModel(filterListModel);
    }

    public C1SearchableList(LiveList<T> items, Component action, ListCellConfigurer configurer) {
        this(C1ListFactories.BOX,items,action,configurer);
    }

    private Container newNorthContainer(Component action) {
        return new C1BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(listener);
    }

    @Override
    public Component getComponent() {
        return component;
    }

    public T getSelected() {
        return filterListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
