package common.screenfactories;

import common.Registry;
import common.domain.LocationDescription;
import common.event.LiveList;
import common.event.SwappableList;
import common.page.Page;
import common.screen.PageFactory;
import common.page.ScreenLink;
import common.screen.dynamic.GlobPageFactory;
import common.screenparts.LocationListCellConfigurer;
import common.screens.LocationSelectionPage;
import common.uilist.IListContentInstaller;
import common.uiwidget.ISearchableList;
import common.uiwidget.UILabel;

import java.util.ArrayList;

public final class LocationSelectionScreenFactory {

    public static PageFactory FACTORY = GlobPageFactory.filter("LocationSelection", new PageFactory() {
        @Override
        public Page[] create(ScreenLink link) {
            return new Page[]{of(link)};
        }
    });
            
    static LocationSelectionPage of(ScreenLink link) {
        ISearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionPage(link,searchList);
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
