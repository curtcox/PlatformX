package c1.screens;

import common.app.CurrentState;
import common.Registry;
import common.domain.ServiceProvider;
import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import c1.uilist.SearchableList;

/**
 * The screen used to search for service providers.
 */
public final class SearchScreen
    extends SelectionListScreen<ServiceProvider>
{
    public SearchScreen(ScreenLink link, SearchableList<ServiceProvider> searchList) {
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
