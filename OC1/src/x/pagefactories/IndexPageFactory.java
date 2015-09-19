package x.pagefactories;

import x.app.Registry;
import x.event.LiveList;
import x.page.PageFactory;
import x.page.PageTags;

public final class IndexPageFactory {

    public static PageFactory of(PageTags tags,final LiveList<String> index) {
        return factoryFactory().newFactory(tags,index,ItemToPageLink.TO_STRING);
    }

    private static ItemListPageFactoryFactory factoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }
}
