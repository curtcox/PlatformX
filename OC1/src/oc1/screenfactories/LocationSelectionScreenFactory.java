package oc1.screenfactories;

import com.codename1.ui.Label;
import oc1.domain.LocationDescription;
import oc1.event.LiveList;
import oc1.event.SwappableList;
import oc1.event.SimpleNegotiableList;
import oc1.screenparts.LocationListCellConfigurer;
import oc1.screens.LocationSelectionScreen;
import oc1.uilist.ListContentInstaller;
import oc1.uilist.SearchableList;

/**
 *
 * @author Curt
 */
public final class LocationSelectionScreenFactory {

    public static LocationSelectionScreen withPrevious() {
        SearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(searchList);
    }
    
    private static SearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return new SearchableList(locations,new Label(),new LocationListCellConfigurer());
    }

    private static SearchableList<LocationDescription> newSearchableList() {
        SwappableList<LocationDescription> locations = new SimpleNegotiableList();
        SearchableList<LocationDescription> list = newSearchableList(locations);
        ListContentInstaller.install(list, locations,new GeocoderStringToList());
        return list;
    }

}
