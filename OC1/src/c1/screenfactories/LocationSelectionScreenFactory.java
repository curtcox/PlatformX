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
import common.screenparts.LocationListCellConfigurer;
import common.screens.LocationSelectionScreen;
import c1.uilist.C1ListContentInstaller;
import c1.uilist.C1SearchableList;

public final class LocationSelectionScreenFactory {

    public static ScreenFactory FACTORY = GlobScreenFactory.filter("LocationSelection", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[]{of(link)};
        }
    });
            
    static LocationSelectionScreen of(ScreenLink link) {
        C1SearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(link,searchList);
    }
    
    private static C1SearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return new C1SearchableList(locations,new Label(),new LocationListCellConfigurer());
    }

    private static C1SearchableList<LocationDescription> newSearchableList() {
        SwappableList<LocationDescription> locations = new SimpleSwappableList();
        C1SearchableList<LocationDescription> list = newSearchableList(locations);
        C1ListContentInstaller.install(list, locations, new GeocoderStringToList());
        return list;
    }

}
