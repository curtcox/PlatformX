package oc1.screens;

import com.codename1.ui.Component;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.event.LiveList;
import oc1.screen.Screen;
import oc1.screenparts.ServiceProviderListCellRenderer;
import oc1.screenparts.ZoomOut;
import oc1.stores.ServiceProviders;
import oc1.ui.SearchableList;

/**
 *
 * @author Curt
 */
final class SearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];
    
    static SearchScreen withPrevious(Screen previous) {
        return withPreviousAndRadius(previous,STARTING_RADIUS);    
    }
    
    static SearchScreen withPreviousAndRadius(Screen previous, int radius) {
        ZoomOut zoomOut = zoomOutToSmallestRadiusWithMultipleHits(previous,radius,ALL_TYPES);
        return new SearchScreen(previous,newSearchableList(getProviders(zoomOut,ALL_TYPES),zoomOut.createComponent()));    
    }

    static SearchScreen withPreviousAndType(Screen previous, Type[] types) {
        ZoomOut zoomOut = zoomOutToSmallestRadiusWithMultipleHits(previous,STARTING_RADIUS,types);
        return new SearchScreen(previous,newSearchableList(getProviders(zoomOut,types),zoomOut.createComponent()));    
    }

    private static ZoomOut zoomOutToSmallestRadiusWithMultipleHits(Screen previous,int radius, Type[] types) {
        ZoomOut zoomOut = new ZoomOut(previous,radius);
        LiveList<ServiceProvider> providers = getProviders(zoomOut,types);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            zoomOut = zoomOut.zoomOut();
            providers = getProviders(zoomOut,types);
        }
        return zoomOut;
    }

    private static LiveList<ServiceProvider> getProviders(ZoomOut zoomOut, Type[] types) {
        return ServiceProviders.of().nearby(zoomOut.radius,types);
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(LiveList providers,Component zoom) {
        return new SearchableList(providers,zoom,new ServiceProviderListCellRenderer());
    }

}
