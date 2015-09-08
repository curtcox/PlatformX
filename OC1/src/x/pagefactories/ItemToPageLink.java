package x.pagefactories;

import x.page.Page;
import x.page.PageLink;

public interface ItemToPageLink {

    PageLink pageLink(Object item);

    ItemToPageLink PAGE = new ItemToPageLink() {
        @Override
        public PageLink pageLink(Object item) {
            if (item instanceof Page) {
                return useSelectedPage((Page) item);
            }
            throw new IllegalArgumentException(item + " is not a Page");
        }

        private PageLink useSelectedPage(Page page) {
            return PageLink.of(page,page.toString());
        }

    };

    ItemToPageLink TO_STRING = new ItemToPageLink() {
        @Override
        public PageLink pageLink(Object item) {
            return PageLink.of(item.toString());
        }
    };

}

