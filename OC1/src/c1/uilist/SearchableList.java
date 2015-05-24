package c1.uilist;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.ListModel;
import c1.event.LiveList;
import c1.uiwidget.BorderContainer;
import common.event.Action;
import common.uiwidget.ISearchableList;

/**
 * A wrapper for a searchable list component.
 * @param <T> list item type
 */
public final class SearchableList<T>
    implements ISearchableList<T>
{

    final TextField searchTerm = new TextField();
    final FilterListModel<T> filterListModel;
    private final ListModel<T> underlyingListModel;
    private final IList filteredList;

    /**
     * The component itself, for embedding in a Screen.
     */
    public final Component component;

    private SearchableList(IList.Factory factory, LiveList<T> items, Component action, ListCellConfigurer configurer) {
        underlyingListModel = VirtualListModel.of(items);
        filterListModel = new FilterListModel(underlyingListModel);
        filteredList = factory.of(filterListModel,configurer);
        component = new BorderContainer((Component)filteredList)
             .addNorth(newNorthContainer(action));
    }

    public SearchableList(LiveList<T> items, Component action, ListCellConfigurer configurer) {
        this(IList.BOX,items,action,configurer);
    }

    private Container newNorthContainer(Component action) {
        return new BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(final Action.Listener listener) {
        filteredList.addActionListener(converted(listener));
    }

    private static ActionListener converted(final Action.Listener listener) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.actionPerformed(new Action(actionEvent));
            }
        };
    }

    public T getSelected() {
        return filterListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
