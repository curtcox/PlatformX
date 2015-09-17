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
import x.uiwidget.XSearchableList;

import java.util.ArrayList;

public final class LocationSelectionPageFactory {

    public static PageFactory FACTORY = GlobPageFactory.filter("LocationSelection", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{of(link)};
        }
    });
            
    static LocationSelectionPage of(PageLink link) {
        XSearchableList<LocationDescription> searchList = newSearchableList();
        return new LocationSelectionPage(link,searchList);
    }
    
    private static XSearchableList<LocationDescription> newSearchableList(LiveList locations) {
        return searchableListBuilder()
                .items(locations)
                .configurer(new LocationListCellConfigurer())
                .build();
    }

    private static XSearchableList<LocationDescription> newSearchableList() {
        SwappableList<LocationDescription> locations = swappableListFactory().from(new ArrayList());
        XSearchableList<LocationDescription> list = newSearchableList(locations);
        contentInstaller().install(list, locations, new GeocoderStringToList());
        return list;
    }

    private static SwappableList.Factory swappableListFactory() {
        return Registry.get(SwappableList.Factory.class);
    }

    private static XSearchableList.Builder searchableListBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }

    private static IListContentInstaller contentInstaller() {
        return Registry.get(IListContentInstaller.class);
    }

}
