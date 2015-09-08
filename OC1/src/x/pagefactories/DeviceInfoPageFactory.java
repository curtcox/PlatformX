package x.pagefactories;

import x.Registry;
import x.device.DeviceKeyValuePair;
import x.device.IDeviceInfo;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

public final class DeviceInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory()
                .newFactory(deviceInfo().asDeviceKeyValuePairs(),new DeviceKeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static IDeviceInfo deviceInfo() {
        return Registry.get(IDeviceInfo.class);
    }

    static final class DeviceKeyValuePairToPageLink
        implements ItemToPageLink
    {
        @Override
        public PageLink pageLink(Object item) {
            DeviceKeyValuePair pair = (DeviceKeyValuePair) item;
            return Page.withFixedLayout(title(pair),layout(pair)).link;
        }

        private String title(DeviceKeyValuePair pair) {
            return pair.key;
        }

        private XComponent layout(DeviceKeyValuePair pair) {
            return new XLabel("=" +pair.value);
        }
    }
}
