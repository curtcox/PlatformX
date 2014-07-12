package oc1.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ListModel;

/**
 *
 * @author Curt
 */
final class BoxList
    extends Container
    implements IList
{
    private final ListModel model;
    private ActionListener listener;
    private int selectedIndex;
    
    BoxList(ListModel model, ListCellConfigurer configurer) {
        this.model = model;
        addCellsFromModel(configurer);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    }
    
    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private void addCellsFromModel(ListCellConfigurer configurer) {
        for (int i=0; i<model.getSize(); i++) {
            this.addComponent(newListCell(configurer,i));
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
                listener.actionPerformed(event);
            }
        });
    }

}
