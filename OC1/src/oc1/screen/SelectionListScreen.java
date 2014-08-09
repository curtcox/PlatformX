package oc1.screen;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.log.LogManager;
import oc1.uilist.SearchableList;

/**
 *
 * @author Curt
 */
public abstract class SelectionListScreen<T>
    extends Screen
{
    private final SearchableList<T> searchList;

    public SelectionListScreen(String name,Screen previous, SearchableList<T> searchList) { 
        super(name,previous);
        this.searchList = searchList;
        addSelectionListener();
    }

    @Override
    public void layoutForPortrait() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                log("selected " + getSelected());
                useSelectedItem(getSelected());
            }
        });
    }

    private T getSelected() {
        return searchList.getSelected();
    }
    
    protected abstract void useSelectedItem(T item);
    
    private void log(String message) {
        LogManager.of().getLog(SelectionListScreen.class).log(message);    
    }

}
