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
import common.util.StringMap;
import c1.screenfactories.*;
import c1.screens.*;

/**
 * The top-level ScreenFactory.
 */
public final class RootScreenFactory {
    
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
                new DeviceInfoScreenFactory(),
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                FilterScreenFactory.FACTORY,
                SearchScreenFactory.FACTORY,
                CustomComponentScreen.FACTORY,
                dynamicScreens(layouts),
                new IndexScreenFactory(index),
                new LazyScreenFactory(taggedLayouts)
        );
    }
    
    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                    .map(ScreenTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
