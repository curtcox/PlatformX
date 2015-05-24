package common.uiwidget;

import com.codename1.ui.events.ActionListener;

public interface ISearchableList<T> {
    void onSelected(ActionListener actionListener);
    T getSelected();
}
