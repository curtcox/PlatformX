package x.pageparts;

import x.domain.LocationDescription;
import x.uilist.IXListCell;

public final class LocationListCellConfigurer
    implements IXListCell.ConfigProducer<LocationDescription>
{

    @Override
    public IXListCell.Config configFor(LocationDescription location) {
        return new IXListCell.Config(location.address);
    }
}
