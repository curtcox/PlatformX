package oc1.screenparts;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.List;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import oc1.domain.ServiceProvider;
import oc1.ui.Icons;
import oc1.ui.MultiButtonListCellRenderer;

/**
 *
 * @author Curt
 */
public final class ServiceProviderListCellRenderer
    extends MultiButtonListCellRenderer
{

    public ServiceProviderListCellRenderer() {
        super();
    }

    @Override
    public Component getListCellRendererComponent(List list, Object value, int index, boolean isSelected) {
        MultiButton component = (MultiButton) super.getCellRendererComponent(list, value, value, index, isSelected);
        ServiceProvider provider = (ServiceProvider) value;
        configureButton(component,provider);
        return component;
    }

    private void configureButton(MultiButton button, ServiceProvider provider) {
        button.setTextLine1(ratingAndDistance(provider));
        button.setIcon(Icons.of().getImage(provider.icon));
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
    
}
