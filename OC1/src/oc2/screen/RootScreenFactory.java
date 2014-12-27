package oc2.screen;

import java.util.Arrays;
import java.util.List;
import common.Registry;
import common.screen.ScreenFactory;
import oc1.app.Version;
import oc1.screen.*;
import oc1.util.StringMap;
import oc2.screenfactories.*;
import oc2.screens.*;

/**
 * The top-level ScreenFactory.
 * @author Curt
 */
public final class RootScreenFactory {
    
    public static List<String> index = Arrays.asList(
        "Device_Info",
        "LocationSelection","ProviderDetails",
        "Filter","Search","Custom"
    );
    
    public static ScreenFactory of() {
        return of(Registry.get(StringMap.class));    
    }
    
    public static ScreenFactory of(StringMap layouts) {
        return new CompositeScreenFactory(
                new DeviceInfoScreenFactory(),
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                FilterScreenFactory.FACTORY,
                SearchScreenFactory.FACTORY,
                CustomComponentScreen.FACTORY,
                dynamicScreens(layouts),
                new IndexScreenFactory(),
                new LazyScreenFactory(layouts)
        );
    }
    
    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                    .map(""     ,Version.VERSION, new Home(), new StringMapStringSource(layouts,"Home"))
                .build();
    }
}
