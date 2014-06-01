package com.mycompany.myapp.screens;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.List;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.mycompany.myapp.domain.ServiceProvider;

/**
 *
 * @author Curt
 */
final class ServiceProviderListCellRenderer
    implements ListCellRenderer
{

    final MultiButton selected = new MultiButton();
    final MultiButton unselected = new MultiButton();
    final ListCellRenderer renderer = new GenericListCellRenderer(selected, unselected);

    public Component getListCellRendererComponent(List list, Object value, int index, boolean isSelected) {
        ServiceProvider provider = (ServiceProvider) value;
        MultiButton component = (MultiButton) renderer.getListCellRendererComponent(list, provider.name.toString(), index, isSelected);
        configureButton(component,provider);
        return component;
    }

    private void configureButton(MultiButton button, ServiceProvider provider) {
        button.setTextLine1(ratingAndDistance(provider));
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
    
    public Component getListFocusComponent(List list) {
        return renderer.getListFocusComponent(list);
    }
    
}
