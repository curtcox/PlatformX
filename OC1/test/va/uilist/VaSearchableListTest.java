package va.uilist;

import config.ShouldRun;
import fake.FakeIosRegistryLoader;
import ios.uilist.IosSearchableList;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.uikit.UIViewController;
import x.event.LiveList;
import x.event.XLiveList;
import x.uilist.IXListCell;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class VaSearchableListTest {

    List list = new ArrayList();
    LiveList items = XLiveList.of(list);
    UIViewController action;
    IXListCell.ConfigProducer configurer;

    IosSearchableList testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
        FakeIosRegistryLoader.load();
        action = new UIViewController();
        testObject = IosSearchableList.of(items,action,configurer);
    }

    @Test
    public void getComponent_returns_a_component() {
        assertTrue(testObject.getComponent() instanceof UIViewController);
    }
}