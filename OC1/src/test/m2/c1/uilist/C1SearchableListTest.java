package c1.uilist;

import com.codename1.ui.Component;
import config.ShouldRun;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;
import x.event.LiveList;
import x.event.XLiveList;
import x.uilist.IXListCell;
import x.uiwidget.XSearchableList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class C1SearchableListTest {

    List list = new ArrayList();
    LiveList items = XLiveList.of(list);
    Component action;
    IXListCell.ConfigProducer configurer;

    XSearchableList testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeUIManager.of();
        action = new Component() {};
        testObject = C1SearchableList.of(items,action,configurer);
    }

    @Test
    public void getComponent_returns_a_component() {
        assertTrue(testObject.getComponent() instanceof Component);
    }
}