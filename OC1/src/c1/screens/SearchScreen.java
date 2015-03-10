package c1.screens;

import common.app.CurrentState;
import common.Registry;
import common.domain.ServiceProvider;
import common.screen.ScreenLink;
import c1.screen.SelectionListScreen;
import c1.uilist.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
public final class SearchScreen
    extends SelectionListScreen<ServiceProvider>
{
    public SearchScreen(SearchableList<ServiceProvider> searchList) { 
        super("Search",searchList);
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
