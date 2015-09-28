package x.specificpagefactories;

import x.app.Registry;
import x.domain.ConsumerServiceProvider;
import x.domain.Type;
import x.event.SwappableList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.dynamic.GlobPageFactory;
import x.pageparts.ServiceProviderListCellConfigurer;
import x.pageparts.ServiceProviderSearchParams;
import x.pageparts.ServiceProviderTextFilter;
import x.pages.ServiceProviderSearchPage;
import x.services.XConsumerServiceProviders;
import x.uilist.XSearchFilterInstaller;
import x.uiwidget.XSearchableList;

import java.util.Arrays;
import java.util.List;

public final class ServiceProviderSearchScreenFactory {
    
    private static final int STARTING_RADIUS = 100;
    private static final Type[] ALL_TYPES = new Type[0];

    public static PageFactory FACTORY = GlobPageFactory.filter("Search", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{searchScreenFromArgs(link, link.args)};
        }
    });
        
    private static ServiceProviderSearchPage of(PageLink link) {
        return withTypesAndRadius(link,ALL_TYPES,STARTING_RADIUS);
    }
    
    private static Page searchScreenFromArgs(PageLink link,Object[] args) {
        if (args.length==0) return ServiceProviderSearchScreenFactory.of(link);
        if (args.length==1) return ServiceProviderSearchScreenFactory.withTypes(link, types(args));
        if (args.length==2) return ServiceProviderSearchScreenFactory.withTypesAndRadius(link, types((Object[]) args[0]), (Integer) args[1]);
        throw new IllegalArgumentException("args=" + Arrays.asList(args));
    }

    public static ServiceProviderSearchPage withTypesAndRadius(PageLink link,Type[] types, int radius) {
        ServiceProviderSearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,radius);
        return new ServiceProviderSearchPage(link,newSearchableList(searchParams));
    }

    private static Type[] types(Object[] objects) {
        Type[] types = new Type[objects.length];
        for (int i=0; i<objects.length; i++) {
            types[i] = (Type) objects[i];
        }
        return types;
    }
    
    public static ServiceProviderSearchPage withTypes(PageLink link,Type[] types) {
        ServiceProviderSearchParams searchParams = zoomOutToSmallestRadiusWithMultipleHits(types,STARTING_RADIUS);
        return new ServiceProviderSearchPage(link,newSearchableList(searchParams));
    }

    private static ServiceProviderSearchParams zoomOutToSmallestRadiusWithMultipleHits(Type[] types, int radius) {
        ServiceProviderSearchParams searchParams = new ServiceProviderSearchParams(types,radius);
        List<ConsumerServiceProvider> providers = getProviders(searchParams);
        while (searchParams.couldZoomOut() && providers.size()<2) {
            searchParams = searchParams.zoomOut();
            providers = getProviders(searchParams);
        }
        return searchParams;
    }
    
    private static XSearchableList<ConsumerServiceProvider> newSearchableList(ServiceProviderSearchParams searchParams)
    {
        SwappableList<ConsumerServiceProvider> swappable = newSwappableList(searchParams);
        XSearchableList list = listBuilder()
            .items(swappable)
            .action(new ZoomOutSearchButton(searchParams,swappable))
            .configurer(new ServiceProviderListCellConfigurer())
            .build();
        installer().install(list, new ServiceProviderTextFilter());
        return list;
    }

    private static SwappableList<ConsumerServiceProvider> newSwappableList(
        ServiceProviderSearchParams searchParams)
    {
        return swappableListFactory().from(serviceProviders().nearby(searchParams.types, searchParams.radius));
    }

    private static XSearchFilterInstaller installer() {
        return Registry.get(XSearchFilterInstaller.class);
    }

    private static XSearchableList.Builder listBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }

    private static SwappableList.Factory swappableListFactory() {
        return Registry.get(SwappableList.Factory.class);
    }

    private static List<ConsumerServiceProvider> getProviders(ServiceProviderSearchParams searchParams) {
        return XConsumerServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }

    private static XConsumerServiceProviders serviceProviders() {
        return Registry.get(XConsumerServiceProviders.class);
    }
}
