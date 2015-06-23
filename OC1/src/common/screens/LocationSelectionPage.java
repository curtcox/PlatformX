package common.screens;

import common.Registry;
import common.domain.LocationDescription;
import common.page.ScreenLink;
import common.page.SelectionListPage;
import common.services.LocationService;
import common.uiwidget.ISearchableList;

/**
 * The screen used to search for locations.
 */
public final class LocationSelectionPage
    extends SelectionListPage<LocationDescription>
{

    public LocationSelectionPage(ScreenLink link, ISearchableList<LocationDescription> searchList) {
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
