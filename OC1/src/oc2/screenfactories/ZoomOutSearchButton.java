package oc2.screenfactories;

import java.util.List;
import oc1.domain.ServiceProvider;
import oc1.event.SwappableList;
import oc1.screenparts.SearchParams;
import oc1.services.ServiceProviders;
import oc1.ui.ActionButton;

final class ZoomOutSearchButton
    extends ActionButton
{
    private SearchParams searchParams;
    private final SwappableList providers;

    public ZoomOutSearchButton(SearchParams searchParams, SwappableList providers) {
        super(searchParams.zoomText());
        this.searchParams = searchParams;
        this.providers = providers;
    }

    @Override
    public void onTap() {
        if (searchParams.couldZoomOut()) {
            zoomOut();
        }
    }

    private void zoomOut() {
        searchParams = searchParams.zoomOut();
        setText(searchParams.zoomText());
        providers.become(getProviders(searchParams));
    }
    
    private static List<ServiceProvider> getProviders(SearchParams searchParams) {
        return ServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }


}
