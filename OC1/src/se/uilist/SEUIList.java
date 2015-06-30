package se.uilist;

import x.event.Action;
import x.uilist.ListCellConfigurer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

final class SEUIList<T>
    extends JList<T>
{
    SEUIList(ListModel model) {
        super(model);
    }

    public static SEUIList of(ListModel model,ListCellConfigurer configurer) {
        SEUIList list = new SEUIList(model);
        list.setCellRenderer(new SEBasicListCellRenderer(configurer));
        return list;
    }

    public void addActionListener(final Action.Listener listener) {
        addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listener.actionPerformed(new Action(SEUIList.this));
            }
        });
    }

}
