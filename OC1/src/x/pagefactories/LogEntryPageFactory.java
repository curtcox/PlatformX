package x.pagefactories;

import x.Registry;
import x.log.XLogWriter;
import x.page.PageFactory;
import x.page.PageTags;

public final class LogEntryPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Log"),
                logEntries().asKeyValuePairs(),
                new KeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static KeyValuePairListSource logEntries() {
        return Registry.get(XLogWriter.class);
    }

}
