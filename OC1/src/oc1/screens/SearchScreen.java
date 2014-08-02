package oc1.screens;

import oc1.screenfactories.SearchScreenFactory;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.log.LogManager;
import oc1.screen.Screen;
import oc1.ui.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
public final class SearchScreen
    extends Screen
{
    private final SearchableList<ServiceProvider> searchList;

    public SearchScreen(Screen previous,SearchableList<ServiceProvider> searchList) { 
        super("Search",previous);
        this.searchList = searchList;
        addSelectionListener();
    }

    public static SearchScreen withPreviousAndTypes(Screen previous, Type[] types) {
        return SearchScreenFactory.withPreviousAndTypes(previous, types);
    }

    public static SearchScreen withPreviousTypesAndRadius(Screen previous, Type[] types, int radius) {
        return SearchScreenFactory.withPreviousTypesAndRadius(previous, types, radius);
    }
    
    @Override
    public void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                useSelectedProvider();
                ProviderDetailsScreen.linkBackTo(SearchScreen.this).show();
            }
        });
    }

    private void useSelectedProvider() {
        ServiceProvider provider = searchList.getSelected();
        log("selected " + provider);
        Registry.put(ServiceProvider.class,provider);
        CurrentState.get().broadcastChange();
    }
    
    private void log(String message) {
        LogManager.of().getLog(SearchScreen.class).log(message);    
    }

}
