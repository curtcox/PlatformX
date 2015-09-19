package x.pagefactories;

import x.app.Registry;
import x.event.LiveList;
import x.log.XLogWriter;
import x.page.PageFactory;
import x.page.PageTags;

public final class LogEntryPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Log"),
                logEntries(),
                NamedValuePageFactory.ITEM_TO_PAGELINK);
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static LiveList logEntries() {
        return Registry.get(XLogWriter.class).log();
    }

}
