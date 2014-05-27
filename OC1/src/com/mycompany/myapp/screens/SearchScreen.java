package com.mycompany.myapp.screens;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.stores.ServiceProviders;
import com.mycompany.myapp.ui.ActionButton;
import com.mycompany.myapp.ui.SearchableList;

/**
 * The screen used to search for service providers.
 * @author Curt
 */
final class SearchScreen
    extends Screen
{
    final int radius;
    final SearchableList<ServiceProvider> searchList;

    SearchScreen(Screen previous) { 
        this(previous,100);
    }
    
    SearchScreen(Screen previous,int radius) { 
        super("Search",previous);
        this.radius = radius;
        searchList = newSearchableList(radius);
        layoutForm();
        addSelectionListener();
    }
    
    private void layoutForm() {
        form.addComponent(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useSelectedProvider();
                SearchScreen.this.back();
            }
        });
    }

    private void useSelectedProvider() {
        Registry.put(ServiceProvider.class,searchList.getSelected());
        CurrentState.get().broadcastChange();
    }

    private SearchableList<ServiceProvider> newSearchableList(int radius) {
        return new SearchableList(ServiceProviders.of().nearby(radius),newZoomOutButton());
    }

    private ActionButton newZoomOutButton() {
        return ScreenButton.lazyWithTextAndLeadingTo("+",newZoomOutLink());
    }

    private ScreenFactory newZoomOutLink() {
        return new ScreenFactory() {
            public Screen create() { return new SearchScreen(SearchScreen.this.previous, radius * 4); }
        };
    }
}
