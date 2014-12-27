package oc1.screenparts;

import common.domain.LocationDescription;
import oc1.uilist.ListCell;
import oc1.uilist.ListCellConfigurer;

public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(ListCell button, LocationDescription location) {
        button.firstRow.setText(location.address);
    }

}
