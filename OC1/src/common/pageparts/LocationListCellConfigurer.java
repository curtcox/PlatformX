package common.pageparts;

import common.domain.LocationDescription;
import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;

public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(IListCell button, LocationDescription location) {
        button.setFirstRowText(location.address);
    }

}
