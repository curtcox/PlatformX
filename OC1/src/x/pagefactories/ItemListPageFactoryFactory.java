package x.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;

/**
 * For creating ItemList PageS from LiveLists.
 */
public interface ItemListPageFactoryFactory {

    /**
     * A LiveList is needed, rather than just a list, so that there is a mechanism
     * to listen for updates.
     */
    PageFactory newFactory(LiveList values);
}
