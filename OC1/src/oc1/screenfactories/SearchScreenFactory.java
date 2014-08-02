package oc1.screenfactories;

import com.codename1.ui.Component;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.event.LiveList;
import oc1.screen.Screen;
import oc1.screenparts.ServiceProviderListCellConfigurer;
import oc1.screenparts.ServiceProviderTextFilter;
import oc1.screenparts.ZoomOut;
import oc1.screens.SearchScreen;
import oc1.services.ServiceProviders;
import oc1.uilist.SearchFilterInstaller;
import oc1.uilist.SearchableList;

/**
 *
 * @author Curt
 */
public final class SearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];
    
    public static SearchScreen withPrevious(Screen previous) {
        return withPreviousTypesAndRadius(previous,ALL_TYPES,STARTING_RADIUS);    
    }
    
    public static SearchScreen withPreviousTypesAndRadius(Screen previous, Type[] types, int radius) {
        ZoomOut zoomOut = zoomOutToSmallestRadiusWithMultipleHits(previous,types,radius);
        return new SearchScreen(previous,newSearchableList(getProviders(zoomOut,types),zoomOut.createComponent()));    
    }

    public static SearchScreen withPreviousAndTypes(Screen previous, Type[] types) {
        ZoomOut zoomOut = zoomOutToSmallestRadiusWithMultipleHits(previous,types,STARTING_RADIUS);
        return new SearchScreen(previous,newSearchableList(getProviders(zoomOut,types),zoomOut.createComponent()));    
    }

    private static ZoomOut zoomOutToSmallestRadiusWithMultipleHits(Screen previous, Type[] types, int radius) {
        ZoomOut zoomOut = new ZoomOut(previous,types,radius);
        LiveList<ServiceProvider> providers = getProviders(zoomOut,types);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            zoomOut = zoomOut.zoomOut();
            providers = getProviders(zoomOut,types);
        }
        return zoomOut;
    }

    private static LiveList<ServiceProvider> getProviders(ZoomOut zoomOut, Type[] types) {
        return ServiceProviders.of().nearby(types,zoomOut.radius);
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(LiveList providers,Component zoom) {
        SearchableList list = new SearchableList(providers,zoom,new ServiceProviderListCellConfigurer());
        SearchFilterInstaller.install(list, new ServiceProviderTextFilter());
        return list;
    }

}
