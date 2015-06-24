package common.pages;

import common.Registry;
import common.domain.LocationDescription;
import common.page.PageLink;
import common.page.SelectionListPage;
import common.services.LocationService;
import common.uiwidget.ISearchableList;

/**
 * The screen used to search for locations.
 */
public final class LocationSelectionPage
    extends SelectionListPage<LocationDescription>
{

    public LocationSelectionPage(PageLink link, ISearchableList<LocationDescription> searchList) {
        super(link,searchList);
    }

    @Override
    protected PageLink useSelectedItem(LocationDescription item) {
        locationService().selectLocation(item.toLocation());
        return PageLink.of("ProviderDetails", item);
    }

    private LocationService locationService() {
        return Registry.get(LocationService.class);
    }
}
