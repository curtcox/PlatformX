package com.mycompany.myapp.screens;

import com.codename1.ui.Component;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.event.LiveList;
import com.mycompany.myapp.stores.ServiceProviders;
import com.mycompany.myapp.ui.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
final class SearchScreen
    extends Screen
{
    final int radius;
    final SearchableList<ServiceProvider> searchList;

    private SearchScreen(Screen previous,int radius, SearchableList<ServiceProvider> searchList) { 
        super("Search",previous);
        this.radius = radius;
        this.searchList = searchList;
        layoutForm();
        addSelectionListener();
    }

    static SearchScreen of(Screen previous) {
        return of(previous,100);    
    }
    
    static SearchScreen of(Screen previous, int radius) {
        ZoomOut zoomOut = new ZoomOut(previous,radius);
        LiveList<ServiceProvider> providers = ServiceProviders.of().nearby(radius);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            radius *= 4;
            providers = ServiceProviders.of().nearby(radius);
        }
        Component zoom = zoomOut.createComponent();
        SearchableList<ServiceProvider> searchList = newSearchableList(providers,zoom);
        return new SearchScreen(previous,radius,searchList);    
    }

    static LiveList<ServiceProvider> getProviders(ZoomOut zoomOut) {
        LiveList<ServiceProvider> providers = ServiceProviders.of().nearby(zoomOut.radius);
        while (zoomOut.couldZoomOut() && providers.size()<2) {
            zoomOut = zoomOut.zoomOut();
            providers = ServiceProviders.of().nearby(zoomOut.radius);
        }
        return providers;
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(LiveList providers,Component zoom) {
        return new SearchableList(providers,zoom,new ServiceProviderListCellRenderer());
    }

    private void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useSelectedProvider();
                new ProviderDetailsScreen(SearchScreen.this).show();
            }
        });
    }

    private void useSelectedProvider() {
        Registry.put(ServiceProvider.class,searchList.getSelected());
        CurrentState.get().broadcastChange();
    }
}
