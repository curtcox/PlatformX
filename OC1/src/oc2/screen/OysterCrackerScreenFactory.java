package oc2.screen;

import java.util.Arrays;
import oc1.app.Version;
import oc1.domain.Type;
import oc1.log.LogManager;
import oc1.net.URIs;
import oc1.screen.CompositeScreenFactory;
import oc1.screen.DynamicScreenFactory;
import oc1.screen.Screen;
import oc1.screen.ScreenFactory;
import oc1.screen.ScreenLink;
import oc1.screen.StringMapStringSource;
import oc1.util.StringMap;
import oc2.net.SimpleNetStringMap;
import oc2.screenfactories.FilterScreenFactory;
import oc2.screenfactories.LocationSelectionScreenFactory;
import oc2.screenfactories.SearchScreenFactory;
import oc2.screens.HomeScreenController;
import oc2.screens.HowToScreen;
import oc2.screens.ProviderDetailsScreen;
import oc2.screens.RateScreen;

public final class OysterCrackerScreenFactory
    implements ScreenFactory
{
    public static ScreenFactory of() {
        return of(new SimpleNetStringMap(URIs.URI("http://localhost:8000/")));    
    }
    
    public static ScreenFactory of(StringMap layouts) {
        return new CompositeScreenFactory(
                DynamicScreenFactory.builder()
                    .map("",Version.VERSION,new HomeScreenController(),new StringMapStringSource(layouts,"Home"))
                .build(),
                new OysterCrackerScreenFactory());
    }
    
    public Screen create(ScreenLink link) {
        return create(link.screen,link.args);
    }

    private Screen create(String screen, Object[] args) {
        log("screen="+screen + "args=" + Arrays.asList(args));
        String lower = screen.toLowerCase();
        if ("search".equals(lower))            return searchScreenFromArgs(args);
        if ("locationselection".equals(lower)) return LocationSelectionScreenFactory.withPrevious();
        if ("filter".equals(lower))            return FilterScreenFactory.withPrevious();
        if ("howto".equals(lower))             return new HowToScreen();
        if ("providerdetails".equals(lower))   return new ProviderDetailsScreen();
        if ("rate".equals(lower))              return RateScreen.withPrevious();
        throw new IllegalArgumentException(screen);
    }
    
    private void log(String message) {
        LogManager.of().getLog(ScreenFactory.class).log(message);    
    }

    private Screen searchScreenFromArgs(Object[] args) {
        if (args.length==0) return SearchScreenFactory.withPrevious();
        if (args.length==1) return SearchScreenFactory.withPreviousAndTypes((Type[])args);
        if (args.length==2) return SearchScreenFactory.withPreviousTypesAndRadius((Type[])args[0],(Integer)args[1]);
        throw new IllegalArgumentException("args=" + Arrays.asList(args));
    }

}
