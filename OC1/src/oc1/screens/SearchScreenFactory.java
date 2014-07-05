package oc1.screens;

import com.codename1.ui.Component;
import oc1.domain.ServiceProvider;
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
public class SearchScreenFactory {
    
    public static SearchScreen withPrevious(Screen previous) {
        return withPreviousAndRadius(previous,100);    
    }
    
    public static SearchScreen withPreviousAndRadius(Screen previous, int radius) {
        ZoomOut zoomOut = zoomOutToSmallestRadiusWithMultipleHits(previous,radius);
        return new SearchScreen(previous,newSearchableList(getProviders(zoomOut),zoomOut.createComponent()));    
    }

    private static ZoomOut zoomOutToSmallestRadiusWithMultipleHits(Screen previous,int radius) {
        ZoomOut zoomOut = new ZoomOut(previous,radius);
        LiveList<ServiceProvider> providers = getProviders(zoomOut);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            zoomOut = zoomOut.zoomOut();
            providers = getProviders(zoomOut);
        }
        return zoomOut;
    }

    private static LiveList<ServiceProvider> getProviders(ZoomOut zoomOut) {
        return ServiceProviders.of().nearby(zoomOut.radius);
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(LiveList providers,Component zoom) {
        return new SearchableList(providers,zoom,new ServiceProviderListCellRenderer());
    }

}
