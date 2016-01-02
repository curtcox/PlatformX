package x.pagefactories;

import x.page.PageFactory;

public final class PageFactoryBuilder {

    final PageFactory first;

    private PageFactoryBuilder(PageFactory first) {
        this.first = first;
    }

    public static PageFactoryBuilder firstCheck(PageFactory... factories) {
        return new PageFactoryBuilder(first(factories));
    }

    private static PageFactory first(PageFactory... factories) {
        return new FirstMatchingPageCompositePageFactory(factories);
    }

    public PageFactory thenCheck(PageFactory... factories) {
        return first(first,new MatchingIndexedPagesCompositePageFactory(factories));
    }

}
