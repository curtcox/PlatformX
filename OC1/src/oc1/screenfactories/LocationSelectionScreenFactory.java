package oc1.screenfactories;

import com.codename1.ui.Label;
import java.util.List;
import oc1.domain.LocationDescription;
import oc1.event.LiveList;
import oc1.event.SimpleLiveList;
import oc1.screen.Screen;
import oc1.screenparts.LocationListCellConfigurer;
import oc1.screens.LocationSelectionScreen;
import oc1.services.Geocoder;
import oc1.uilist.SearchableList;
import oc1.uilist.StringToListFilter;

/**
 *
 * @author Curt
 */
public final class LocationSelectionScreenFactory {

    public static LocationSelectionScreen withPrevious(Screen previous) {
        SearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(previous,searchList);
    }
    
    private static SearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return new SearchableList(locations,new Label(),new LocationListCellConfigurer(),StringToListFilter.ALLOW_ALL);
    }

    private static SearchableList<LocationDescription> newSearchableList() {
        List<LocationDescription> locations = Geocoder.of().searchFor("Portland");
        return newSearchableList(new SimpleLiveList(locations));
    }

}
