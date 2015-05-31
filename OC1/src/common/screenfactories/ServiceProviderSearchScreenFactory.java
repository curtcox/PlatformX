package common.screenfactories;

import java.util.*;

import common.screenparts.ServiceProviderSearchParams;
import common.screenparts.ServiceProviderTextFilter;
import common.services.ServiceProviders;
import common.Registry;
import common.domain.*;
import common.event.SwappableList;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import common.screens.ServiceProviderSearchScreen;
import common.uilist.ISearchFilterInstaller;
import common.uiwidget.ISearchableList;

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
        ServiceProviderSearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
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
        ServiceProviderSearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,STARTING_RADIUS);
        return new ServiceProviderSearchScreen(link,newSearchableList(getProviders(searchParams),searchParams));
    }

    private static ServiceProviderSearchParams zoomOutToSmallestRadiusWithMultipleHits(Type[] types, int radius) {
        ServiceProviderSearchParams searchParams = new ServiceProviderSearchParams(types,radius);
        List<ServiceProvider> providers = getProviders(searchParams);
        while (searchParams.couldZoomOut() && providers.size()<2) {
            searchParams = searchParams.zoomOut();
            providers = getProviders(searchParams);
        }
        return searchParams;
    }
    
    private static ISearchableList<ServiceProvider> newSearchableList(List<ServiceProvider> providers,ServiceProviderSearchParams searchParams) {
        SwappableList<ServiceProvider> swappable = newSwappableList(providers);
        ZoomOutSearchButton zoomButton = new ZoomOutSearchButton(searchParams,swappable);
        ISearchableList list = null;//new SearchableList(swappable,zoomButton,new ServiceProviderListCellConfigurer());
        installer().install(list, new ServiceProviderTextFilter());
        return list;
    }

    private static SwappableList<ServiceProvider> newSwappableList(List<ServiceProvider> providers) {
        return null;
    }

    private static ISearchFilterInstaller installer() {
        return Registry.get(ISearchFilterInstaller.class);
    }

    private static List<ServiceProvider> getProviders(ServiceProviderSearchParams searchParams) {
        return ServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }

    private static ServiceProviders serviceProviders() {
        return Registry.get(ServiceProviders.class);
    }
}
