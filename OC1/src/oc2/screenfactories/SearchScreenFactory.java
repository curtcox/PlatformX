package oc2.screenfactories;

import java.util.*;
import oc1.domain.*;
import oc1.event.*;
import oc1.screen.*;
import oc1.screenparts.*;
import oc2.screens.SearchScreen;
import oc1.services.ServiceProviders;
import oc1.uilist.*;

public final class SearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];

    public static ScreenFactory FACTORY = new GlobScreenFactory("Search") {
        public Screen doCreate(ScreenLink link) {
            return searchScreenFromArgs(link.args);
        }     
    };
        
    private static SearchScreen of() {
        return withTypesAndRadius(ALL_TYPES,STARTING_RADIUS);    
    }
    
    private static Screen searchScreenFromArgs(Object[] args) {
        if (args.length==0) return SearchScreenFactory.of();
        if (args.length==1) return SearchScreenFactory.withTypes(types(args));
        if (args.length==2) return SearchScreenFactory.withTypesAndRadius(types((Objects[])args[0]),(Integer)args[1]);
        throw new IllegalArgumentException("args=" + Arrays.asList(args));
    }

    public static SearchScreen withTypesAndRadius(Type[] types, int radius) {
        SearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
        return new SearchScreen(newSearchableList(getProviders(searchParams),searchParams));    
    }

    private static Type[] types(Object[] objects) {
        Type[] types = new Type[objects.length];
        for (int i=0; i<objects.length; i++) {
            types[i] = (Type) objects[i];
        }
        return types;
    }
    
    public static SearchScreen withTypes(Type[] types) {
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
