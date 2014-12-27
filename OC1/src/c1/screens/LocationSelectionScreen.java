package c1.screens;

import common.domain.LocationDescription;
import common.screen.ScreenLink;
import c1.screen.SelectionListScreen;
import c1.services.Locations;
import c1.uilist.SearchableList;

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
