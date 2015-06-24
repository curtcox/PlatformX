package x.pageparts;

import x.domain.LocationDescription;
import x.uilist.IListCell;
import x.uilist.ListCellConfigurer;

public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(IListCell button, LocationDescription location) {
        button.setFirstRowText(location.address);
    }

}
