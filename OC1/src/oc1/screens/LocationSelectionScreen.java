package oc1.screens;

import oc1.domain.LocationDescription;
import oc1.screen.Screen;
import oc1.screen.SelectionListScreen;
import oc1.screenfactories.LocationSelectionScreenFactory;
import oc1.screenfactories.SearchScreenFactory;
import oc1.services.Locations;
import oc1.uilist.SearchableList;

/**
 * The screen used to search for locations.
 * @author Curt
 */
public final class LocationSelectionScreen
    extends SelectionListScreen<LocationDescription>
{

    public LocationSelectionScreen(Screen previous, SearchableList<LocationDescription> searchList) { 
        super("Pick Location",previous,searchList);
    }

    static LocationSelectionScreen withPrevious(Screen screen) {
        return LocationSelectionScreenFactory.withPrevious(screen);
    }
    
    @Override
    protected void useSelectedItem(LocationDescription item) {
        Locations.of().selectLocation(item.toLocation());
        SearchScreenFactory.withPrevious(LocationSelectionScreen.this).show();
    }

}
