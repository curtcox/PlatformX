package oc1.screen;

import com.codename1.ui.events.*;
import oc1.app.Registry;
import oc1.log.LogManager;
import oc1.uilist.SearchableList;

/**
 * A screen for selecting an item from a list of items.
 * Implementors must 
 * @author Curt
 * @param <T> the type of things in this list
 */
public abstract class SelectionListScreen<T>
    extends Screen
{
    private final SearchableList<T> searchList;

    public SelectionListScreen(String name, SearchableList<T> searchList) { 
        super(name);
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
                followLink(useSelectedItem(getSelected()));
            }
        });
    }

    private void followLink(ScreenLink link) {
        screenFactory().create(link).show();
    }

    private T getSelected() {
        return searchList.getSelected();
    }

    /**
     * Provides a link to the screen for the selected item.
     * @param item the selected item
     * @return link for the next screen
     */
    protected abstract ScreenLink useSelectedItem(T item);
    
    private void log(String message) {
        LogManager.of().getLog(SelectionListScreen.class).log(message);    
    }

    private static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }

}
