package x.pagefactories;

import x.page.PageFactory;

public final class PageFactoryBuilder {

    public static PageFactoryBuilder firstCheck(PageFactory... factories) {
        return new PageFactoryBuilder();
    }

    public PageFactory thenCheck(PageFactory... factories) {
        return new MatchingIndexedPagesCompositePageFactory(factories);
    }

}
