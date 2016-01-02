package x.pages;

import x.app.Registry;
import x.app.CurrentState;
import x.domain.ConsumerServiceProvider;
import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * The screen used to search for service providers.
 */
public final class ServiceProviderSearchPage
    extends SelectionListPage<ConsumerServiceProvider>
{
    public ServiceProviderSearchPage(PageLink link, XSearchableList<ConsumerServiceProvider> searchList) {
        super(link,searchList);
    }

    private void useSelectedProvider(ConsumerServiceProvider provider) {
        Registry.put(ConsumerServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    @Override
    protected PageLink useSelectedItem(ConsumerServiceProvider item) {
        useSelectedProvider(item);
        return PageLink.of("ProviderDetails", item);
    }

}
