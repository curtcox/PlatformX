package c1.screenfactories;

import com.codename1.ui.Label;
import common.domain.LocationDescription;
import c1.event.LiveList;
import common.event.SwappableList;
import c1.event.SimpleSwappableList;
import common.screen.dynamic.GlobScreenFactory;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import c1.screenparts.LocationListCellConfigurer;
import c1.screens.LocationSelectionScreen;
import c1.uilist.ListContentInstaller;
import c1.uilist.SearchableList;

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
