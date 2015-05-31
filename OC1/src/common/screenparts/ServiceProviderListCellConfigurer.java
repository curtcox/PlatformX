package common.screenparts;

import common.domain.ServiceProvider;
import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;

public final class ServiceProviderListCellConfigurer
    implements ListCellConfigurer<ServiceProvider>
{

    public void configureButton(IListCell button, ServiceProvider provider) {
        button.setFirstRowText(ratingAndDistance(provider));
        button.setSecondRowText(provider.toString());
        button.setIcon(provider.icon);
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
}
