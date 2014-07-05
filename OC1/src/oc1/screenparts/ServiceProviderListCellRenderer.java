package oc1.screenparts;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.List;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import oc1.domain.ServiceProvider;
import oc1.ui.BasicListCellRenderer;
import oc1.ui.Icons;
import oc1.ui.ListCell;

/**
 *
 * @author Curt
 */
public final class ServiceProviderListCellRenderer
    extends BasicListCellRenderer
{

    public ServiceProviderListCellRenderer() {
        super();
    }

    @Override
    public Component getListCellRendererComponent(List list, Object value, int index, boolean isSelected) {
        ListCell component = (ListCell) super.getCellRendererComponent(list, value, value, index, isSelected);
        ServiceProvider provider = (ServiceProvider) value;
        configureButton(component,provider);
        return component;
    }

    private void configureButton(ListCell button, ServiceProvider provider) {
        button.firstRow.setText(ratingAndDistance(provider));
        button.secondRow.setText(provider.toString());
        button.icon.setIcon(Icons.of().getImage(provider.icon));
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
    
}
