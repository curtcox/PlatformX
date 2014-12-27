package oc2.screenfactories;

import com.codename1.ui.Label;
import common.domain.LocationDescription;
import oc1.event.LiveList;
import common.event.SwappableList;
import oc1.event.SimpleSwappableList;
import oc1.screen.GlobScreenFactory;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import oc1.screenparts.LocationListCellConfigurer;
import oc2.screens.LocationSelectionScreen;
import oc1.uilist.ListContentInstaller;
import oc1.uilist.SearchableList;

public final class LocationSelectionScreenFactory {

    public static ScreenFactory FACTORY = new GlobScreenFactory("LocationSelection") {
        @Override
        protected Screen doCreate(ScreenLink link) {
            return of(); 
        }
    };
            
    static LocationSelectionScreen of() {
        SearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(searchList);
    }
    
    private static SearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return new SearchableList(locations,new Label(),new LocationListCellConfigurer());
    }

    private static SearchableList<LocationDescription> newSearchableList() {
        SwappableList<LocationDescription> locations = new SimpleSwappableList();
        SearchableList<LocationDescription> list = newSearchableList(locations);
        ListContentInstaller.install(list, locations,new GeocoderStringToList());
        return list;
    }

}
