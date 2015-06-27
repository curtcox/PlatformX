package x.page;

import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.page.dynamic.DynamicPageFactory;
import x.page.dynamic.LazyPageFactory;
import x.page.dynamic.StringMapStringSource;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.*;
import x.pages.CustomComponentPage;
import x.pages.Home;
import x.pages.ProviderDetailsPage;
import x.util.StringMap;

import java.util.Arrays;

/**
 * The top-level PageFactory.
 */
public final class RootPageFactory {

    private static LiveList index = new XLiveList(Arrays.asList(
            "Device_Info",
            "LocationSelection", "ProviderDetails",
            "Filter", "Search", "Custom")
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
