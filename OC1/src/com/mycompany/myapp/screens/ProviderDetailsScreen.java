package com.mycompany.myapp.screens;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.domain.ServiceProvider;
import java.util.Arrays;

/**
 * For showing details about a particular provider.
 * @author Curt
 */
final class ProviderDetailsScreen
    extends Screen
{
    private final Label name = new Label();
    private final Label distance = new Label();
    private final Label types = new Label();
    private final Label price = new Label();
    private final Label icon = new Label();
    private final Label vicinity = new Label();

    ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
        form.setLayout(new GridLayout(7,1));
        form.addComponent(name);
        form.addComponent(distance);
        form.addComponent(vicinity);
        form.addComponent(price);
        form.addComponent(icon);
        form.addComponent(types);
        form.addComponent(ProviderRatingButton.withReturnTo(this));
    }
    
    @Override
    protected void refresh() {
        updateName();
        updatePrice();
        updateIcon();
        updateTypes();
        updateDistance();
        updateVicinity();
    }

    private void updateName() {
        name.setText(provider().name.toString());
    }
    
    private void updatePrice() {
        price.setText("" + provider().priceLevel);
    }

    private void updateIcon() {
        icon.setText("" + provider().icon);
    }
    
    private void updateTypes() {
        types.setText(Arrays.asList(provider().types).toString());
    }

    private void updateDistance() {
        distance.setText(provider().distanceFromCurrentLocation());
    }

    private void updateVicinity() {
        vicinity.setText(provider().address);
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
}
