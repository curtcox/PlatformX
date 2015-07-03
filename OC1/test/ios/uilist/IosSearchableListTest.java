package ios.uilist;

import config.ShouldRun;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.uikit.UIView;
import x.event.LiveList;
import x.event.XLiveList;
import x.uilist.ListCellConfigurer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class IosSearchableListTest {

    List list = new ArrayList();
    LiveList items = new XLiveList(list);
    UIView action;
    ListCellConfigurer configurer;

    IosSearchableList testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
        FakeAnRegistryLoader.load();
        action = new UIView();
        testObject = IosSearchableList.of(items,action,configurer);
    }

    @Test
    public void getComponent_returns_a_component() {
        assertTrue(testObject.getComponent() instanceof UIView);
    }
}