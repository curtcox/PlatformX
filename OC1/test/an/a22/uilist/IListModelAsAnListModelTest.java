package an.a22.uilist;

import fake.FakeDataSetObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.uilist.XListModel;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class IListModelAsAnListModelTest {

    FakeDataSetObserver dataChangedListener = new FakeDataSetObserver();
    XListModel model = new XListModel();
    IListModelAsAnListModel testObject = new IListModelAsAnListModel(model);

    @Before
    public void setUp() {
        testObject.registerDataSetObserver(dataChangedListener);
    }

    @Test
    public void empty_list_has_size_0() {
        assertEquals(0,testObject.getCount());
    }

    @Test
    public void empty_list_is_empty() {
        assertTrue(testObject.isEmpty());
    }

    @Test
    public void adding_one_object_to_empty_list_makes_size_1() {
        model.addItem("one");

        assertEquals(1, testObject.getCount());
    }

    @Test
    public void adding_one_object_to_empty_list_makes_it_not_empty() {
        model.addItem("one");

        assertFalse(testObject.isEmpty());
    }

    @Test
    public void getItemAt_returns_item_at_index() {
        Object expected = "item";
        model.addItem(expected);

        assertSame(expected, testObject.getItem(0));
    }

}