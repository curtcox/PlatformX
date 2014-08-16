package oc2.screen;

import java.util.Arrays;
import oc1.app.Version;
import oc1.domain.Type;
import oc1.log.LogManager;
import oc1.screen.LayoutScreen;
import oc1.screen.Screen;
import oc1.screen.ScreenFactory;
import oc1.screen.ScreenLink;
import oc2.screenfactories.FilterScreenFactory;
import oc2.screenfactories.LocationSelectionScreenFactory;
import oc2.screenfactories.SearchScreenFactory;
import oc2.screens.HomeScreenController;
import oc2.screens.HomeScreenLayout;
import oc2.screens.HowToScreen;
import oc2.screens.ProviderDetailsScreen;
import oc2.screens.RateScreen;

/**
 *
 * @author Curt
 */
public final class OysterCrackerScreenFactory
    implements ScreenFactory
{
    public Screen create(ScreenLink link) {
        return create(link.screen,link.args);
    }

    private Screen create(String screen, Object[] args) {
        log("screen="+screen + "args=" + Arrays.asList(args));
        String lower = screen.toLowerCase();
        if ("".equals(lower))                  return new LayoutScreen(Version.VERSION,new HomeScreenController(),new HomeScreenLayout());
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
