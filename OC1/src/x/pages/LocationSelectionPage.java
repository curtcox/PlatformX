package x.pages;

import x.app.Registry;
import x.domain.LocationDescription;
import x.page.PageLink;
import x.page.SelectionListPage;
import x.services.LocationService;
import x.uiwidget.XSearchableList;

/**
 * The screen used to search for locations.
 */
public final class LocationSelectionPage
    extends SelectionListPage<LocationDescription>
{

    public LocationSelectionPage(PageLink link, XSearchableList<LocationDescription> searchList) {
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
