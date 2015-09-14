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

    private static LiveList index = XLiveList.of(Arrays.asList(
            "Device_Info",
            "LocationSelection", "ProviderDetails",
            "Filter", "Search", "Custom")
    );

    public static PageFactory of() {
        return pageFactory();
    }

    private static PageFactory pageFactory() {
        return pageFactory(Registry.get(StringMap.class), Registry.get(TaggedStringSources.class));
    }

    private static PageFactory pageFactory(StringMap layouts, TaggedStringSources taggedLayouts) {
        return new MatchingIndexedPagesCompositePageFactory(
                DeviceInfoPageFactory.of(),
                LogEntryPageFactory.of(),
                RegistryInfoPageFactory.of(),
                LocationSelectionPageFactory.FACTORY,
                ProviderDetailsPage.FACTORY,
                ServiceProviderFilterPageFactory.FACTORY,
                ServiceProviderSearchScreenFactory.FACTORY,
                CustomComponentPage.FACTORY,
                dynamicScreens(layouts),
                IndexPageFactory.of(PageTags.of("Index"),index),
                new LazyPageFactory(taggedLayouts)
        );
    }

    private static PageFactory dynamicScreens(StringMap layouts) {
        return DynamicPageFactory.builder()
                .map(PageTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
