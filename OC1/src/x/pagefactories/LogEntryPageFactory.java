package x.pagefactories;

import x.Registry;
import x.event.LiveList;
import x.log.XLogEntry;
import x.log.XLogWriter;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;

public final class LogEntryPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Log"),
                logEntries(),
                new LogEntryToPageLink());
    }

    static final class LogEntryToPageLink
            implements ItemToPageLink
    {
        @Override
        public PageLink pageLink(Object item) {
            XLogEntry value = (XLogEntry) item;
            return NamedValuePageFactory.linkTo(new NamedValue("entry",value));
        }

    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static LiveList logEntries() {
        return Registry.get(XLogWriter.class).log();
    }

}
