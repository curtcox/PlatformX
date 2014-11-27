package oc2.screen;

import oc1.app.Registry;
import oc1.app.Version;
import oc1.screen.*;
import oc1.util.StringMap;
import oc2.screenfactories.*;
import oc2.screens.*;

public final class RootScreenFactory {
    
    public static ScreenFactory of() {
        return of(Registry.get(StringMap.class));    
    }
    
    public static ScreenFactory of(StringMap layouts) {
        return new CompositeScreenFactory(
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                FilterScreenFactory.FACTORY,
                SearchScreenFactory.FACTORY,
                dynamicScreens(layouts),
                new LazyScreenFactory(layouts)
        );
    }
    
    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                    .map(""     ,Version.VERSION, new Home(), new StringMapStringSource(layouts,"Home"))
                .build();
    }
}
