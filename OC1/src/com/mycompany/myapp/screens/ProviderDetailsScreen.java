package com.mycompany.myapp.screens;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.domain.ServiceProvider;

/**
 * For showing details about a particular provider.
 * @author Curt
 */
final class ProviderDetailsScreen
    extends Screen
{

    private final Label name = new Label("name");
    private final Label distance = new Label("distance");

    ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
        form.setLayout(new GridLayout(3,1));
        form.addComponent(name);
        form.addComponent(distance);
        form.addComponent(ProviderRatingButton.withReturnTo(this));
    }
    
    @Override
    protected void refresh() {
        updateName();
        updateDistance();
    }

    private void updateName() {
        name.setText(provider().name.toString());
    }

    private void updateDistance() {
        distance.setText(provider().distanceFromCurrentLocation());
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
}
