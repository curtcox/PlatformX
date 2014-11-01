package oc2.screen;

import oc1.app.Version;
import oc1.net.URIs;
import oc1.screen.*;
import oc1.util.StringMap;
import oc2.net.SimpleNetStringMap;
import oc2.screenfactories.*;
import oc2.screens.*;

public final class OysterCrackerScreenFactory {
    
    public static ScreenFactory of() {
        return of(new SimpleNetStringMap(URIs.URI("http://localhost:8000/")));    
    }
    
    public static ScreenFactory of(StringMap layouts) {
        return new CompositeScreenFactory(
                LocationSelectionScreenFactory.FACTORY,
                RateScreen.FACTORY,
                ProviderDetailsScreen.FACTORY,
                FilterScreenFactory.FACTORY,
                SearchScreenFactory.FACTORY,
                dynamicScreens(layouts)
        );
    }
    
    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                    .map(""     ,Version.VERSION, new HomeScreenController(), new StringMapStringSource(layouts,"Home"))
                    .map("HowTo","How To"       , new HowToScreenController(),new StringMapStringSource(layouts,"HowTo"))
                .build();
    }
}
