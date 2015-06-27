package an.a22.uilist;

import android.content.Context;
import android.view.View;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.uilist.ListCellConfigurer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnSearchableListTest {

    List list = new ArrayList();
    LiveList items = new XLiveList(list);
    View action;
    ListCellConfigurer configurer;

    AnSearchableList testObject;

    @Before
    public void setUp() {
        FakeAnRegistryLoader.load();
        action = new View(context());
        testObject = new AnSearchableList(items,action,configurer);
    }

    private Context context() {
        return Registry.get(Context.class);
    }

    @Test
    public void getComponent_returns_a_component() {
        assertTrue(testObject.getComponent() instanceof View);
    }
}