package x.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.page.PageTags;

/**
 * For creating ItemList PageS from LiveLists.
 */
public interface ItemListPageFactoryFactory {

    /**
     * A LiveList is needed, rather than just a list, so that there is a mechanism
     * to listen for updates.
     */
    PageFactory newFactory(PageTags tags, LiveList values, ItemToPageLink itemToPageLink);
}
