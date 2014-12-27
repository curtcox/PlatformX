package c1.screenparts;

import common.domain.LocationDescription;
import c1.uilist.ListCell;
import c1.uilist.ListCellConfigurer;

public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(ListCell button, LocationDescription location) {
        button.firstRow.setText(location.address);
    }

}
