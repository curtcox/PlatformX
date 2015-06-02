package c1.screenfactories;

import common.Registry;
import common.domain.LocationDescription;
import c1.event.LiveList;
import common.event.SwappableList;
import common.screen.dynamic.GlobScreenFactory;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screenparts.LocationListCellConfigurer;
import common.screens.LocationSelectionScreen;
import common.uilist.IListContentInstaller;
import common.uiwidget.ISearchableList;
import common.uiwidget.UILabel;

import java.util.ArrayList;

public final class LocationSelectionScreenFactory {

    public static ScreenFactory FACTORY = GlobScreenFactory.filter("LocationSelection", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[]{of(link)};
        }
    });
            
    static LocationSelectionScreen of(ScreenLink link) {
        ISearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionScreen(link,searchList);
    }
    
    private static ISearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return searchableListFactory().from(locations, new UILabel(), new LocationListCellConfigurer());
    }

    private static ISearchableList<LocationDescription> newSearchableList() {
        SwappableList<LocationDescription> locations = swappableListFactory().from(new ArrayList());
        ISearchableList<LocationDescription> list = newSearchableList(locations);
        contentInstaller().install(list, locations, new GeocoderStringToList());
        return list;
    }

    private static SwappableList.Factory swappableListFactory() {
        return Registry.get(SwappableList.Factory.class);
    }

    private static ISearchableList.Factory searchableListFactory() {
        return Registry.get(ISearchableList.Factory.class);
    }

    private static IListContentInstaller contentInstaller() {
        return Registry.get(IListContentInstaller.class);
    }

}
