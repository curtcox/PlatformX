package fake;

import x.event.Action;
import x.uiwidget.XSearchableList;

public class FakeSearchableList implements XSearchableList {

    @Override
    public void onSelected(Action.Listener actionListener) {

    }

    @Override
    public Object getComponent() {
        return null;
    }

    @Override
    public Object getSelected() {
        return null;
    }
}
