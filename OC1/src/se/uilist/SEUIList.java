package se.uilist;

import x.event.Action;
import x.uilist.UIList;

import javax.swing.*;

final class SEUIList<T>
    extends JList<T>
    implements UIList
{
    SEUIList(ListModel model) {
        super(model);
    }

    @Override
    public void addActionListener(Action.Listener listener) {

    }

    public void setRenderer(SEBasicListCellRenderer renderer) {

    }
}
