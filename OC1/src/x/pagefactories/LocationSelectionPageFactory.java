package x.pagefactories;

import x.Registry;
import x.domain.LocationDescription;
import x.event.LiveList;
import x.event.SwappableList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.dynamic.GlobPageFactory;
import x.pageparts.LocationListCellConfigurer;
import x.pages.LocationSelectionPage;
import x.uilist.IListContentInstaller;
import x.uiwidget.ISearchableList;
import x.uiwidget.UILabel;

import java.util.ArrayList;

public final class LocationSelectionPageFactory {

    public static PageFactory FACTORY = GlobPageFactory.filter("LocationSelection", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{of(link)};
        }
    });
            
    static LocationSelectionPage of(PageLink link) {
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
