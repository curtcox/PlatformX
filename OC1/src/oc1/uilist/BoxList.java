package oc1.uilist;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ListModel;

/**
 * For displaying items from a ListModel.
 * This class exists as an attempt to bypass a UI freeze with List.
 * @author Curt
 */
final class BoxList
    extends Container
    implements IList
{
    private final ListModel model;
    private ActionListener actionListener;
    private final ListCellConfigurer configurer;
    private int selectedIndex;
    
    BoxList(ListModel model, ListCellConfigurer configurer) {
        this.model = model;
        this.configurer = configurer;
        addCellsFromModel();
        addDataChangedListener();
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    }
    
    private void addDataChangedListener() {
        model.addDataChangedListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                addCellsFromModel();
                BoxList.this.repaint();
            }
        });
    }
    
    public void addActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private void addCellsFromModel() {
        removeAll();
        for (int i=0; i<model.getSize(); i++) {
            addComponent(newListCell(configurer,i));
        }
    }

    private ListCell newListCell(ListCellConfigurer configurer,final int index) {
        ListCell cell = new ListCell();
        configurer.configureButton(cell, model.getItemAt(index));
        cell.setLeadComponent(cell.firstRow);
        addSelectedListener(cell.firstRow,index);
        return cell;
    }

    private void addSelectedListener(Button button,final int index) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                selectedIndex = index;
                actionListener.actionPerformed(event);
            }
        });
    }
}
