package oc1.screen;

import java.util.Arrays;
import oc1.domain.Type;
import oc1.log.LogManager;
import oc1.screenfactories.FilterScreenFactory;
import oc1.screenfactories.LocationSelectionScreenFactory;
import oc1.screenfactories.SearchScreenFactory;
import oc1.screens.HomeScreen;
import oc1.screens.HowToScreen;
import oc1.screens.SearchScreen;

/**
 *
 * @author Curt
 */
public final class SimpleScreenFactory
    implements ScreenFactory
{

    public Screen create(String screen, Screen previous, Object... args) {
        log("screen="+screen + "args=" + Arrays.asList(args));
        String lower = screen.toLowerCase();
        if ("".equals(lower))                        return new HomeScreen();
        if ("searchscreen".equals(lower))            return searchScreenFromArgs(previous,args);
        if ("locationselectionscreen".equals(lower)) return LocationSelectionScreenFactory.withPrevious(previous);
        if ("filterscreen".equals(lower))            return FilterScreenFactory.withPrevious(previous);
        if ("howtoscreen".equals(lower))             return new HowToScreen(previous);
        throw new IllegalArgumentException(screen);
    }
    
    private void log(String message) {
        LogManager.of().getLog(ScreenFactory.class).log(message);    
    }

    private Screen searchScreenFromArgs(Screen previous, Object[] args) {
        if (args.length==0) return SearchScreenFactory.withPrevious(previous);
        if (args.length==2) return SearchScreenFactory.withPreviousTypesAndRadius(previous,(Type[])args[0],(Integer)args[1]);
        throw new IllegalArgumentException("args=" + Arrays.asList(args));
    }

}
