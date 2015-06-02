package common.uiwidget;

import common.event.Action;
import common.uilist.ListCellConfigurer;

import java.util.List;

public interface ISearchableList<T> {

    interface Factory {
        ISearchableList from(List items, UIComponent action, ListCellConfigurer configurer);
    }

    void onSelected(Action.Listener actionListener);
    T getSelected();
}
