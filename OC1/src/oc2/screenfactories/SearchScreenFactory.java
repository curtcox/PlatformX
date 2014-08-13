package oc2.screenfactories;

import java.util.List;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.event.SimpleSwappableList;
import oc1.event.SwappableList;
import oc1.screenparts.SearchParams;
import oc1.screenparts.ServiceProviderListCellConfigurer;
import oc1.screenparts.ServiceProviderTextFilter;
import oc2.screens.SearchScreen;
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
    
    public static SearchScreen withPrevious() {
        return withPreviousTypesAndRadius(ALL_TYPES,STARTING_RADIUS);    
    }
    
    public static SearchScreen withPreviousTypesAndRadius(Type[] types, int radius) {
        SearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
        return new SearchScreen(newSearchableList(getProviders(searchParams),searchParams));    
    }

    public static SearchScreen withPreviousAndTypes(Type[] types) {
        SearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,STARTING_RADIUS);
        return new SearchScreen(newSearchableList(getProviders(searchParams),searchParams));    
    }

    private static SearchParams zoomOutToSmallestRadiusWithMultipleHits(Type[] types, int radius) {
        SearchParams searchParams = new SearchParams(types,radius);
        List<ServiceProvider> providers = getProviders(searchParams);
        while (searchParams.couldZoomOut() && providers.size()<2) {
            searchParams = searchParams.zoomOut();
            providers = getProviders(searchParams);
        }
        return searchParams;
    }
    
    private static SearchableList<ServiceProvider> newSearchableList(List<ServiceProvider> providers,SearchParams searchParams) {
        SwappableList<ServiceProvider> swappable = new SimpleSwappableList(providers);
        ZoomOutSearchButton zoomButton = new ZoomOutSearchButton(searchParams,swappable);
        SearchableList list = new SearchableList(swappable,zoomButton,new ServiceProviderListCellConfigurer());
        SearchFilterInstaller.install(list, new ServiceProviderTextFilter());
        return list;
    }

    private static List<ServiceProvider> getProviders(SearchParams searchParams) {
        return ServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }

}
