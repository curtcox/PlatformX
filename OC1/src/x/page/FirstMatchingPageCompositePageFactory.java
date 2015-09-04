package x.page;

import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.util.Arrays;

/**
 * A PageFactory that delegates to other ScreenFactories.
 */
public final class FirstMatchingPageCompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;
    
    public FirstMatchingPageCompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }
    
    /**
     * Return a Screen from the first containing factory that matches the link,
     * or throw an IllegalArgumentException if none exists.
     */
    public Page[] create(PageLink link) {
        for (PageFactory factory : factories) {
            Page[] pages = factory.create(link);
            if (pages!=null) {
                log(pages + " created by " + factory);
                return pages;
            }
        }
        String message = "No screen for " + link + " in " + Arrays.asList(factories);
        throw new IllegalArgumentException(message);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(FirstMatchingPageCompositePageFactory.class);
    }

}
