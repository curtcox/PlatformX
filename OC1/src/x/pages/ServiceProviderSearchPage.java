package x.pages;

import x.app.Registry;
import x.app.CurrentState;
import x.domain.ServiceProvider;
import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * The screen used to search for service providers.
 */
public final class ServiceProviderSearchPage
    extends SelectionListPage<ServiceProvider>
{
    public ServiceProviderSearchPage(PageLink link, XSearchableList<ServiceProvider> searchList) {
        super(link,searchList);
    }

    private void useSelectedProvider(ServiceProvider provider) {
        Registry.put(ServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    @Override
    protected PageLink useSelectedItem(ServiceProvider item) {
        useSelectedProvider(item);
        return PageLink.of("ProviderDetails", item);
    }

}
