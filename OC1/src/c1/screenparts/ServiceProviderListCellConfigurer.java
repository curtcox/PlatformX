package c1.screenparts;

import common.domain.ServiceProvider;
import c1.ui.Icons;
import c1.uilist.*;

public final class ServiceProviderListCellConfigurer
    implements ListCellConfigurer<ServiceProvider>
{

    public void configureButton(ListCell button, ServiceProvider provider) {
        button.firstRow.setText(ratingAndDistance(provider));
        button.secondRow.setText(provider.toString());
        button.icon.setIcon(Icons.of().getImage(provider.icon));
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
    
}
