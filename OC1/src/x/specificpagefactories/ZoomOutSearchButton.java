package x.specificpagefactories;

import x.domain.ConsumerServiceProvider;
import x.event.SwappableList;
import x.pageparts.ServiceProviderSearchParams;
import x.services.XConsumerServiceProviders;
import x.uiwidget.XButton;

import java.util.List;

final class ZoomOutSearchButton
    extends XButton
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
    
    private static List<ConsumerServiceProvider> getProviders(ServiceProviderSearchParams searchParams) {
        return XConsumerServiceProviders.of().nearby(searchParams.types,searchParams.radius);
    }


}
