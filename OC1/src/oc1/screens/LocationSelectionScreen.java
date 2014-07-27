package oc1.screens;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.domain.LocationDescription;
import oc1.log.LogManager;
import oc1.screen.Screen;
import oc1.ui.SearchableList;

/**
 * The screen used to search for locations.
 * @author Curt
 */
public final class LocationSelectionScreen
    extends Screen
{

    private final SearchableList<LocationDescription> searchList;

    LocationSelectionScreen(Screen previous, SearchableList<LocationDescription> searchList) { 
        super("Pick Location",previous);
        this.searchList = searchList;
        addSelectionListener();
    }

    static LocationSelectionScreen withPrevious(Screen screen) {
        return LocationSelectionScreenFactory.withPrevious(screen);
    }

    @Override
    public void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                useSelectedLocation();
                SearchScreenFactory.withPrevious(LocationSelectionScreen.this).show();
            }
        });
    }

    private void useSelectedLocation() {
        LocationDescription location = searchList.getSelected();
        log("selected " + location);
        Registry.put(LocationDescription.class,location);
        CurrentState.get().broadcastChange();
    }
    
    private void log(String message) {
        LogManager.of().getLog(LocationSelectionScreen.class).log(message);    
    }

}
