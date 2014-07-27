package oc1.screens;

import com.codename1.ui.Label;
import java.util.ArrayList;
import java.util.List;
import oc1.domain.LocationDescription;
import oc1.event.LiveList;
import oc1.event.SimpleLiveList;
import oc1.screen.Screen;
import oc1.screenparts.ServiceProviderListCellConfigurer;
import oc1.ui.SearchableList;

/**
 *
 * @author Curt
 */
final class LocationSelectionScreenFactory {

    static LocationSelectionScreen withPrevious(Screen previous) {
        SearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(previous,searchList);
    }
    
    private static SearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return new SearchableList(locations,new Label(),new ServiceProviderListCellConfigurer(),new ServiceProviderTextFilter());
    }

    private static SearchableList<LocationDescription> newSearchableList() {
        List<LocationDescription> locations = new ArrayList<LocationDescription>();
        return newSearchableList(new SimpleLiveList(locations));
    }

}
