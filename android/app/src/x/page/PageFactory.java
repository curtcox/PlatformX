package x.page;

import x.Registry;

/**
 * For creating PageS from PageLinkS.
 */
public interface PageFactory {
    
    PageFactory DEFAULT = new PageFactory() {
        public Page[] create(PageLink link) {
            return Registry.get(PageFactory.class).create(link);
        }
    };
            
    /**
     * Return the Screen for the given link.
     * If this factory doesn't support the given link,
     * it should generally return an empty array.
     */
    Page[] create(PageLink link);

}
