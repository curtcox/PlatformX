package c1.screenfactories;

import java.util.*;

import c1.services.ServiceProviders;
import common.Registry;
import common.domain.*;
import common.event.SwappableList;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import c1.event.*;
import c1.screenparts.*;
import common.screens.ServiceProviderSearchScreen;
import c1.uilist.*;

public final class ServiceProviderSearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];

    public static ScreenFactory FACTORY = GlobScreenFactory.filter("Search", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[]{searchScreenFromArgs(link, link.args)};
        }
    });
        
    private static ServiceProviderSearchScreen of(ScreenLink link) {
        return withTypesAndRadius(link,ALL_TYPES,STARTING_RADIUS);
    }
    
    private static Screen searchScreenFromArgs(ScreenLink link,Object[] args) {
        if (args.length==0) return ServiceProviderSearchScreenFactory.of(link);
        if (args.length==1) return ServiceProviderSearchScreenFactory.withTypes(link, types(args));
        if (args.length==2) return ServiceProviderSearchScreenFactory.withTypesAndRadius(link, types((Object[]) args[0]), (Integer) args[1]);
        throw new IllegalArgumentException("args=" + Arrays.asList(args));
    }

    public static ServiceProviderSearchScreen withTypesAndRadius(ScreenLink link,Type[] types, int radius) {
        SearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
        return new ServiceProviderSearchScreen(link,newSearchableList(getProviders(searchParams),searchParams));
    }

    private static Type[] types(Object[] objects) {
        Type[] types = new Type[objects.length];
        for (int i=0; i<objects.length; i++) {
            types[i] = (Type) objects[i];
        }
        return types;
    }
    
    public static ServiceProviderSearchScreen withTypes(ScreenLink link,Type[] types) {
        SearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,STARTING_RADIUS);
        return new ServiceProviderSearchScreen(link,newSearchableList(getProviders(searchParams),searchParams));
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
        SearchableList list = null;//new SearchableList(swappable,zoomButton,new ServiceProviderListCellConfigurer());
        SearchFilterInstaller.install(list, new ServiceProviderTextFilter());
        return list;
    }

    private static List<ServiceProvider> getProviders(SearchParams searchParams) {
        return ServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }

    private static ServiceProviders serviceProviders() {
        return Registry.get(ServiceProviders.class);
    }
}
