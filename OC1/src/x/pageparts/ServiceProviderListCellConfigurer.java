package x.pageparts;

import x.domain.ConsumerServiceProvider;
import x.uilist.IXListCell;

public final class ServiceProviderListCellConfigurer
    implements IXListCell.ConfigProducer<ConsumerServiceProvider>
{

    private String ratingAndDistance(ConsumerServiceProvider provider) {
        return provider.myRating().toString() + " " + provider.distanceFromCurrentLocation();
    }

    @Override
    public IXListCell.Config configFor(ConsumerServiceProvider provider) {
        return new IXListCell.Config(ratingAndDistance(provider),provider.toString(),provider.icon);
    }
}
