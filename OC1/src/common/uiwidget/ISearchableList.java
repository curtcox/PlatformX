package common.uiwidget;

import common.event.Action;

public interface ISearchableList<T> {
    void onSelected(Action.Listener actionListener);
    T getSelected();
}
