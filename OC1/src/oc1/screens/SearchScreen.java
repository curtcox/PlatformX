package oc1.screens;

import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.screen.ScreenLink;
import oc1.screen.SelectionListScreen;
import oc1.screenfactories.SearchScreenFactory;
import oc1.uilist.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
public final class SearchScreen
    extends SelectionListScreen<ServiceProvider>
{
    public SearchScreen(Screen previous,SearchableList<ServiceProvider> searchList) { 
        super("Search",previous,searchList);
    }

    public static SearchScreen withPreviousAndTypes(Screen previous, Type[] types) {
        return SearchScreenFactory.withPreviousAndTypes(previous, types);
    }

    public static SearchScreen withPreviousTypesAndRadius(Screen previous, Type[] types, int radius) {
        return SearchScreenFactory.withPreviousTypesAndRadius(previous, types, radius);
    }
    
    private void useSelectedProvider(ServiceProvider provider) {
        Registry.put(ServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    @Override
    protected ScreenLink useSelectedItem(ServiceProvider item) {
        useSelectedProvider(item);
        return new ScreenLink("ProviderDetails",this);
    }

}
