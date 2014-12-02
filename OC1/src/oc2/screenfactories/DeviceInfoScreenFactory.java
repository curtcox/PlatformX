package oc2.screenfactories;

import com.codename1.ui.Label;
import oc1.device.*;
import oc1.event.*;
import oc1.screen.*;
import oc1.uilist.*;
import oc1.util.Strings;

public final class DeviceInfoScreenFactory {

    public static ScreenFactory FACTORY = new GlobScreenFactory("Device_Info") {
        public Screen doCreate(ScreenLink link) {
            return new DeviceInfoScreen(newSearchableList());    
        }     
    };

    private static LiveList<DeviceKeyValuePair> getValues() {
        return new SimpleLiveList(DeviceInfo.asDeviceKeyValuePairs());
    }

    private static SearchableList<DeviceKeyValuePair> newSearchableList() {
        SearchableList list = new SearchableList(getValues(),new Label(),new CellConfigurer());
        SearchFilterInstaller.install(list, new TextFilter());
        return list;
    }

static final class DeviceInfoScreen
    extends SelectionListScreen<DeviceKeyValuePair>
{
    public DeviceInfoScreen(SearchableList<DeviceKeyValuePair> values) {
        super("Device Info",values);
    }

    @Override
    protected ScreenLink useSelectedItem(DeviceKeyValuePair item) {
        return null;
    }
}

static final class CellConfigurer
    implements ListCellConfigurer<DeviceKeyValuePair>
{
    public void configureButton(ListCell button, DeviceKeyValuePair pair) {
        button.firstRow.setText(pair.toString());
    }
}

static final class TextFilter
    implements StringToListFilter
{

    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                DeviceKeyValuePair pair = (DeviceKeyValuePair) item;
                return isIn(trimmed, pair);
            }
        };
    }

    private static boolean isIn(String target, Object object) {
        return object!=null && Strings.contains(object.toString().toLowerCase(), target);
    }
}

}
