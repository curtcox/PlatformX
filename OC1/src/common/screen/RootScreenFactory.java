package common.screen;

import java.util.Arrays;
import java.util.List;
import common.Registry;
import common.screen.dynamic.DynamicPageFactory;
import common.screen.dynamic.LazyPageFactory;
import common.screen.dynamic.StringMapStringSource;
import common.screen.dynamic.TaggedStringSources;
import common.screenfactories.*;
import common.screens.CustomComponentScreen;
import common.screens.Home;
import common.screens.ProviderDetailsScreen;
import common.util.StringMap;

/**
 * The top-level PageFactory.
 */
public final class RootScreenFactory {

    private static List<String> index = Arrays.asList(
            "Device_Info",
            "LocationSelection", "ProviderDetails",
            "Filter", "Search", "Custom"
    );

    public static PageFactory of() {
        return of(Registry.get(StringMap.class),Registry.get(TaggedStringSources.class));
    }

    public static PageFactory of(StringMap layouts, TaggedStringSources taggedLayouts) {
        return new CompositePageFactory(
                DeviceInfoScreenFactory.of(),
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                ServiceProviderFilterScreenFactory.FACTORY,
                ServiceProviderSearchScreenFactory.FACTORY,
                CustomComponentScreen.FACTORY,
                dynamicScreens(layouts),
                IndexScreenFactory.of(index),
                new LazyPageFactory(taggedLayouts)
        );
    }

    private static PageFactory dynamicScreens(StringMap layouts) {
        return DynamicPageFactory.builder()
                .map(ScreenTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
