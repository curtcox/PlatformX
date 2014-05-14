package com.mycompany.myapp.screens;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.stores.ServiceProviders;
import com.mycompany.myapp.ui.SearchableList;

/**
 *
 * @author Curt
 */
final class SearchScreen
    extends Screen
{
    final ServiceProviders serviceProviders = ServiceProviders.of();
    final SearchableList<ServiceProvider> searchList = new SearchableList(serviceProviders.nearby());
    
    SearchScreen(Screen previous) { 
        super("Search",previous);
        layoutForm();
        addSelectionListener();
    }
    
    private void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CurrentState.get().selected = searchList.getSelected();
                SearchScreen.this.back();
            }
        });
    }

}
