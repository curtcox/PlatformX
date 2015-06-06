package se.uilist;

import common.event.Action;
import common.uilist.ListCellConfigurer;
import common.uilist.UIList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For displaying items from a ListModel.
 * This class exists as an attempt to bypass a UI freeze with List.
 */
final class SEBoxList
    extends JComponent
    implements UIList
{
    private final ListModel model;
    private Action.Listener actionListener;
    private final ListCellConfigurer configurer;
    private int selectedIndex;

    SEBoxList(ListModel model, ListCellConfigurer configurer) {
        this.model = model;
        this.configurer = configurer;
        addCellsFromModel();
        addDataChangedListener();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    
    private void addDataChangedListener() {
        model.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent listDataEvent) {
                dataChanged();
            }

            @Override
            public void intervalRemoved(ListDataEvent listDataEvent) {
                dataChanged();
            }

            @Override
            public void contentsChanged(ListDataEvent listDataEvent) {
                dataChanged();
            }

            void dataChanged() {
                addCellsFromModel();
                SEBoxList.this.repaint();
            }
        });
    }
    
    public void addActionListener(Action.Listener actionListener) {
        this.actionListener = actionListener;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private void addCellsFromModel() {
        removeAll();
        for (int i=0; i<model.getSize(); i++) {
            add(newListCell(configurer, i));
        }
    }

    private SEListCell newListCell(ListCellConfigurer configurer,final int index) {
        SEListCell cell = new SEListCell();
        configurer.configureButton(cell, model.getElementAt(index));
        cell.setLeadComponent(cell.firstRow);
        addSelectedListener(cell.firstRow,index);
        return cell;
    }

    private void addSelectedListener(JButton button,final int index) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                selectedIndex = index;
                actionListener.actionPerformed(new Action(event));
            }

        });
    }
}
