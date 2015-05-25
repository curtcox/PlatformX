package common.screens;

import common.Registry;
import common.domain.LocationDescription;
import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import common.services.LocationService;
import common.uiwidget.ISearchableList;

/**
 * The screen used to search for locations.
 */
public final class LocationSelectionScreen
    extends SelectionListScreen<LocationDescription>
{

    public LocationSelectionScreen(ScreenLink link,ISearchableList<LocationDescription> searchList) {
        super(link,searchList);
    }

    @Override
    protected ScreenLink useSelectedItem(LocationDescription item) {
        locationService().selectLocation(item.toLocation());
        return ScreenLink.of("ProviderDetails",item);
    }

    private LocationService locationService() {
        return Registry.get(LocationService.class);
    }
}
