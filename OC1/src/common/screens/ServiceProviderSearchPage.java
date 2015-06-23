package common.screens;

import common.app.CurrentState;
import common.Registry;
import common.domain.ServiceProvider;
import common.screen.ScreenLink;
import common.screen.SelectionListPage;
import common.uiwidget.ISearchableList;

/**
 * The screen used to search for service providers.
 */
public final class ServiceProviderSearchPage
    extends SelectionListPage<ServiceProvider>
{
    public ServiceProviderSearchPage(ScreenLink link, ISearchableList<ServiceProvider> searchList) {
        super(link,searchList);
    }

    private void useSelectedProvider(ServiceProvider provider) {
        Registry.put(ServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    @Override
    protected ScreenLink useSelectedItem(ServiceProvider item) {
        useSelectedProvider(item);
        return ScreenLink.of("ProviderDetails",item);
    }

}
