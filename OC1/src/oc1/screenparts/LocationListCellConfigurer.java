package oc1.screenparts;

import oc1.domain.LocationDescription;
import oc1.ui.ListCell;
import oc1.ui.ListCellConfigurer;

/**
 *
 * @author Curt
 */
public final class LocationListCellConfigurer
    implements ListCellConfigurer<LocationDescription>
{

    public void configureButton(ListCell button, LocationDescription location) {
        button.firstRow.setText(location.address);
    }

}
