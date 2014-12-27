package oc2.screens;

import common.domain.LocationDescription;
import common.screen.ScreenLink;
import oc1.screen.SelectionListScreen;
import oc1.services.Locations;
import oc1.uilist.SearchableList;

/**
 * The screen used to search for locations.
 * @author Curt
 */
public final class LocationSelectionScreen
    extends SelectionListScreen<LocationDescription>
{

    public LocationSelectionScreen(SearchableList<LocationDescription> searchList) { 
        super("Pick Location",searchList);
    }

    @Override
    protected ScreenLink useSelectedItem(LocationDescription item) {
        Locations.of().selectLocation(item.toLocation());
        return new ScreenLink("ProviderDetails",item);
    }

}
