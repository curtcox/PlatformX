package x.pageparts;

import x.domain.ServiceProvider;
import x.uilist.IXListCell;
import x.uilist.ListCellConfigurer;

public final class ServiceProviderListCellConfigurer
    implements ListCellConfigurer<ServiceProvider>
{

    public void configureButton(IXListCell button, ServiceProvider provider) {
        button.setFirstRowText(ratingAndDistance(provider));
        button.setSecondRowText(provider.toString());
        button.setIcon(provider.icon);
    }

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }
}
