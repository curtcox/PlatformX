package c1.screens;

import common.domain.LocationDescription;
import common.screen.ScreenLink;
import c1.screen.SelectionListScreen;
import c1.services.Locations;
import c1.uilist.SearchableList;

/**
 * The screen used to search for locations.
 */
public final class LocationSelectionScreen
    extends SelectionListScreen<LocationDescription>
{

    public LocationSelectionScreen(ScreenLink link,SearchableList<LocationDescription> searchList) {
        super(link,searchList);
    }

    @Override
    protected ScreenLink useSelectedItem(LocationDescription item) {
        Locations.of().selectLocation(item.toLocation());
        return ScreenLink.of("ProviderDetails",item);
    }

}
