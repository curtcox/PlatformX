package x.pageparts;

import x.domain.ServiceProvider;
import x.uilist.IXListCell;

public final class ServiceProviderListCellConfigurer
    implements IXListCell.ConfigProducer<ServiceProvider>
{

    private String ratingAndDistance(ServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }

    @Override
    public IXListCell.Config configFor(ServiceProvider provider) {
        return new IXListCell.Config(ratingAndDistance(provider),provider.toString(),provider.icon);
    }
}
