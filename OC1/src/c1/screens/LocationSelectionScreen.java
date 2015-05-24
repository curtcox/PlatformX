package c1.screens;

import common.domain.LocationDescription;
import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import c1.services.Locations;
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
        Locations.of().selectLocation(item.toLocation());
        return ScreenLink.of("ProviderDetails",item);
    }

}
