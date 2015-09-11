package x.pagefactories;

import x.Registry;
import x.log.XLogWriter;
import x.page.PageFactory;

public final class LogEntryPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory()
                .newFactory(logEntries().asKeyValuePairs(),new KeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static KeyValuePairListSource logEntries() {
        return Registry.get(XLogWriter.class);
    }

}
