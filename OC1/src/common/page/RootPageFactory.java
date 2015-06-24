package common.page;

import common.Registry;
import common.page.dynamic.DynamicPageFactory;
import common.page.dynamic.LazyPageFactory;
import common.page.dynamic.StringMapStringSource;
import common.page.dynamic.TaggedStringSources;
import common.pagefactories.*;
import common.pages.CustomComponentPage;
import common.pages.Home;
import common.pages.ProviderDetailsPage;
import common.util.StringMap;

import java.util.Arrays;
import java.util.List;

/**
 * The top-level PageFactory.
 */
public final class RootPageFactory {

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
                DeviceInfoPageFactory.of(),
                LocationSelectionPageFactory.FACTORY,
                ProviderDetailsPage.FACTORY,
                ServiceProviderFilterPageFactory.FACTORY,
                ServiceProviderSearchScreenFactory.FACTORY,
                CustomComponentPage.FACTORY,
                dynamicScreens(layouts),
                IndexPageFactory.of(index),
                new LazyPageFactory(taggedLayouts)
        );
    }

    private static PageFactory dynamicScreens(StringMap layouts) {
        return DynamicPageFactory.builder()
                .map(PageTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
