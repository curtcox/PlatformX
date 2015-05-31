package c1.screen;

import java.util.Arrays;
import java.util.List;
import common.Registry;
import common.screen.CompositeScreenFactory;
import common.screen.ScreenFactory;
import common.screen.ScreenTags;
import common.screen.dynamic.DynamicScreenFactory;
import common.screen.dynamic.LazyScreenFactory;
import common.screen.dynamic.StringMapStringSource;
import common.screen.dynamic.TaggedStringSources;
import common.screenfactories.DeviceInfoScreenFactory;
import common.screenfactories.ServiceProviderSearchScreenFactory;
import common.screens.CustomComponentScreen;
import common.screens.Home;
import common.screens.ProviderDetailsScreen;
import common.util.StringMap;
import c1.screenfactories.*;
import common.screenfactories.IndexScreenFactory;

/**
 * The top-level ScreenFactory.
 */
public final class C1RootScreenFactory {
    
    private static List<String> index = Arrays.asList(
        "Device_Info",
        "LocationSelection","ProviderDetails",
        "Filter","Search","Custom"
    );
    
    public static ScreenFactory of() {
        return of(Registry.get(StringMap.class),Registry.get(TaggedStringSources.class));
    }
    
    public static ScreenFactory of(StringMap layouts, TaggedStringSources taggedLayouts) {
        return new CompositeScreenFactory(
                DeviceInfoScreenFactory.of(),
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                ServiceProviderFilterScreenFactory.FACTORY,
                ServiceProviderSearchScreenFactory.FACTORY,
                CustomComponentScreen.FACTORY,
                dynamicScreens(layouts),
                IndexScreenFactory.of(index),
                new LazyScreenFactory(taggedLayouts)
        );
    }
    
    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                    .map(ScreenTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
