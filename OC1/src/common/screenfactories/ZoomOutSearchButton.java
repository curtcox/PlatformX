package common.screenfactories;

import java.util.List;
import common.domain.ServiceProvider;
import common.event.SwappableList;
import common.screenparts.ServiceProviderSearchParams;
import common.services.ServiceProviders;
import common.uiwidget.UIButton;

final class ZoomOutSearchButton
    extends UIButton
{
    private ServiceProviderSearchParams searchParams;
    private final SwappableList providers;

    public ZoomOutSearchButton(ServiceProviderSearchParams searchParams, SwappableList providers) {
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
        text = searchParams.zoomText();
        providers.become(getProviders(searchParams));
    }
    
    private static List<ServiceProvider> getProviders(ServiceProviderSearchParams searchParams) {
        return ServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }


}
