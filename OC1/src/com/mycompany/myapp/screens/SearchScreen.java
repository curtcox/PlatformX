package com.mycompany.myapp.screens;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.stores.ServiceProviders;
import com.mycompany.myapp.ui.ActionButton;
import com.mycompany.myapp.ui.Icons;
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
                new ProviderDetailsScreen(SearchScreen.this).show();
            }
        });
    }

    private void useSelectedProvider() {
        Registry.put(ServiceProvider.class,searchList.getSelected());
        CurrentState.get().broadcastChange();
    }

    private SearchableList<ServiceProvider> newSearchableList(int radius) {
        return new SearchableList(ServiceProviders.of().nearby(radius),newZoom(),new ServiceProviderListCellRenderer());
    }

    private Component newZoom() {
        return couldZoomOut() ? newZoomOutButton() : newZoomLabel();
    }

    private Label newZoomLabel() {
        return new Label(friendlyMeters(radius) + " (Max)");
    }

    private ActionButton newZoomOutButton() {
        return ScreenButton.lazyWithTextAndLeadingTo(friendlyMeters(radius) + " +",newZoomOutLink());
    }

    private ScreenFactory newZoomOutLink() {
        return new ScreenFactory() {
            public Screen create() { return new SearchScreen(SearchScreen.this.previous, radius * 4); }
        };
    }

    private String friendlyMeters(int radius) {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";
        
    }

    private boolean couldZoomOut() {
        return radius * 4 < 30000;
    }
}
