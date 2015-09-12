package x.page;

import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

/**
 * A PageFactory that returns the first matching page from the factories it contains.
 */
final class FirstMatchingPageCompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;
    
    FirstMatchingPageCompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }
    
    public Page[] create(PageLink link) {
        for (PageFactory factory : factories) {
            Page[] pages = factory.create(link);
            if (pages.length > 0) {
                log(pages + " created by " + factory);
                return new Page[] {pages[0]};
            }
        }
        return new Page[0];
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(FirstMatchingPageCompositePageFactory.class);
    }

}
