package c1.uilist;

import com.codename1.ui.Component;
import common.event.CommonLiveList;
import common.event.LiveList;
import common.uilist.ListCellConfigurer;
import fake.FakeUIManager;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class C1SearchableListTest {

    List list = new ArrayList();
    LiveList items = new CommonLiveList(list);
    Component action;
    ListCellConfigurer configurer;

    C1SearchableList testObject;

    @Before
    public void setUp() {
        FakeUIManager.of();
        action = new Component() {};
        testObject = new C1SearchableList(items,action,configurer);
    }

    @Test
    public void getComponent_returns_a_component() {
        assertTrue(testObject.getComponent() instanceof Component);
    }
}