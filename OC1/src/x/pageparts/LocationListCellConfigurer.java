package x.pageparts;

import x.domain.LocationDescription;
import x.uilist.IXListCell;
import x.uilist.ListCellConfigurer;

public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(IXListCell button, LocationDescription location) {
        button.setFirstRowText(location.address);
    }

}
