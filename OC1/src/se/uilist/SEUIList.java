package se.uilist;

import x.event.Action;
import x.uilist.IXListCell;

import javax.swing.*;

final class SEUIList<T>
    extends JList<T>
{
    Action.Listener listener;

    SEUIList(ListModel model) {
        super(model);
    }

    public static SEUIList of(ListModel model,IXListCell.ConfigProducer configurer) {
        SEUIList list = new SEUIList(model);
        list.setCellRenderer(new SEBasicListCellRenderer(configurer));
        return list;
    }

    public void addActionListener(final Action.Listener listener) {
        this.listener = listener;
    }

    public void setSelectionInterval(int anchor, int lead) {
        super.setSelectionInterval(anchor,lead);
        listener.actionPerformed(new Action(SEUIList.this));
    }
}
