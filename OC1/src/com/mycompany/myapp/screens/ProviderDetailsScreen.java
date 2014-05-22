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

    ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
        form.setLayout(new GridLayout(2,1));
        form.addComponent(name);
        form.addComponent(ProviderRatingButton.withReturnTo(this));
    }
    
    @Override
    protected void refresh() {
        updateName();
    }

    private void updateName() {
        name.setText(ServiceProvider.getSelected().name.toString());
    }

}
