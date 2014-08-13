package oc1.screenfactories;

import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.event.LiveList;
import oc1.screenparts.ServiceProviderListCellConfigurer;
import oc1.screenparts.ServiceProviderTextFilter;
import oc1.screenparts.SearchParams;
import oc1.screens.SearchScreen;
import oc1.services.ServiceProviders;
import oc1.ui.ActionButton;
import oc1.uilist.SearchFilterInstaller;
import oc1.uilist.SearchableList;

/**
 *
 * @author Curt
 */
public final class SearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];
    
    public static SearchScreen withPrevious() {
        return withPreviousTypesAndRadius(ALL_TYPES,STARTING_RADIUS);    
    }
    
    public static SearchScreen withPreviousTypesAndRadius(Type[] types, int radius) {
        SearchParams zoomOut = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
        return new SearchScreen(newSearchableList(getProviders(zoomOut,types),zoomOut));    
    }

    public static SearchScreen withPreviousAndTypes(Type[] types) {
        SearchParams zoomOut = zoomOutToSmallestRadiusWithMultipleHits(types,STARTING_RADIUS);
        return new SearchScreen(newSearchableList(getProviders(zoomOut,types),zoomOut));    
    }

    private static SearchParams zoomOutToSmallestRadiusWithMultipleHits(Type[] types, int radius) {
        SearchParams zoomOut = new SearchParams(types,radius);
        LiveList<ServiceProvider> providers = getProviders(zoomOut,types);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            zoomOut = zoomOut.zoomOut();
            providers = getProviders(zoomOut,types);
        }
        return zoomOut;
    }

    private static LiveList<ServiceProvider> getProviders(SearchParams zoomOut, Type[] types) {
        return ServiceProviders.of().nearby(types,zoomOut.radius);
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(LiveList providers,SearchParams zoomOut) {
        ZoomButton zoomButton = new ZoomButton(zoomOut);
        SearchableList list = new SearchableList(providers,zoomButton,new ServiceProviderListCellConfigurer());
        SearchFilterInstaller.install(list, new ServiceProviderTextFilter());
        return list;
    }

    static final class ZoomButton
        extends ActionButton
    {

        public ZoomButton(SearchParams zoom) {
            super(zoom.zoomText());
        }

        @Override
        public void onTap() {
        }
        
    }
}
