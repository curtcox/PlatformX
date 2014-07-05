package oc1.screens;

import oc1.screen.Screen;
import oc1.screenparts.ZoomOut;
import oc1.screenparts.ServiceProviderListCellRenderer;
import com.codename1.ui.Component;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.domain.ServiceProvider;
import oc1.event.LiveList;
import oc1.log.LogManager;
import oc1.stores.ServiceProviders;
import oc1.ui.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
public final class SearchScreen
    extends Screen
{
    private final SearchableList<ServiceProvider> searchList;

    private SearchScreen(Screen previous,SearchableList<ServiceProvider> searchList) { 
        super("Search",previous);
        this.searchList = searchList;
        layoutForm();
        addSelectionListener();
    }

    public static SearchScreen of(Screen previous) {
        return of(previous,100);    
    }
    
    public static SearchScreen of(Screen previous, int radius) {
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

    private void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                useSelectedProvider();
                new ProviderDetailsScreen(SearchScreen.this).show();
            }
        });
    }

    private void useSelectedProvider() {
        ServiceProvider provider = searchList.getSelected();
        log("selected " + provider);
        Registry.put(ServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    private void log(String message) {
        LogManager.of().getLog(SearchScreen.class).log(message);    
    }

}
