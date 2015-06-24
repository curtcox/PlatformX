package common.pages;

import common.app.CurrentState;
import common.Registry;
import common.domain.ServiceProvider;
import common.page.PageLink;
import common.page.SelectionListPage;
import common.uiwidget.ISearchableList;

/**
 * The screen used to search for service providers.
 */
public final class ServiceProviderSearchPage
    extends SelectionListPage<ServiceProvider>
{
    public ServiceProviderSearchPage(PageLink link, ISearchableList<ServiceProvider> searchList) {
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
