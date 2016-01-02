package x.app;

import x.event.LiveList;
import x.event.XLiveList;
import x.page.PageFactory;
import x.page.PageTags;
import x.page.dynamic.DynamicPageFactory;
import x.page.dynamic.LazyPageFactory;
import x.page.dynamic.StringMapStringSource;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.IndexPageFactory;
import x.pagefactories.NamedValuePageFactory;
import x.pagefactories.PageFactoryBuilder;
import x.pages.CustomComponentPage;
import x.pages.Home;
import x.pages.ProviderDetailsPage;
import x.specificpagefactories.*;
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

    private RootPageFactory() {}

    public static PageFactory of() {
        return pageFactory();
    }

    private static PageFactory pageFactory() {
        return pageFactory(Registry.get(StringMap.class), Registry.get(TaggedStringSources.class));
    }

    private static PageFactory pageFactory(StringMap layouts, TaggedStringSources taggedLayouts) {
        return PageFactoryBuilder
        .firstCheck(
            NamedValuePageFactory.of(),
            ServiceProviderSearchScreenFactory.FACTORY,
            ServiceProviderFilterPageFactory.FACTORY,
            LocationSelectionPageFactory.FACTORY,
            ProviderDetailsPage.FACTORY
        )
        .thenCheck(
                DeviceInfoPageFactory.of(),
                LogEntryPageFactory.of(),
                RegistryInfoPageFactory.of(),
                CustomComponentPage.FACTORY,
                dynamicScreens(layouts),
                IndexPageFactory.of(PageTags.of("Index"), index),
                new LazyPageFactory(taggedLayouts)
        );
    }

    private static PageFactory dynamicScreens(StringMap layouts) {
        return DynamicPageFactory.builder()
                .map(PageTags.of("Home"), new Home(), new StringMapStringSource(layouts, "Home"))
                .build();
    }
}
