package an.a22.uiwidget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import config.ShouldRun;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.Registry;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnBorderContainerTest {

    View center;
    AnBorderContainer testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Android);
        FakeAnRegistryLoader.load();
        center = view();
        testObject = AnBorderContainer.of(center);
    }

    @Test
    public void is_LinearLayout() {
        assertTrue(testObject instanceof LinearLayout);
    }

    @Test
    public void center_is_a_child_view() {
        testObject.layout();
        assertTrue(testObject.getChildCount()==1);
        LinearLayout centerRow = (LinearLayout) testObject.getChildAt(0);
        assertSame(center,centerRow.getChildAt(0));
    }

    @Test
    public void adding_child_views_can_be_chained() {
        assertSame(testObject,testObject.north(view()));
        assertSame(testObject,testObject.east(view()));
    }

    @Test
    public void addNorth_adds_the_view_as_a_child_view_when_laid_out() {
        View north = view();
        testObject.north(north).layout();
        assertTrue(testObject.getChildCount() == 2);
        assertSame(north,testObject.getChildAt(0));
    }

    @Test
    public void addEast_adds_the_view_as_a_child_view_when_laid_out() {
        View east = view();
        testObject.east(east).layout();
        assertTrue(testObject.getChildCount()==1);
        LinearLayout centerRow = (LinearLayout) testObject.getChildAt(0);
        assertSame(east,centerRow.getChildAt(1));
    }

    private View view() {
        return new TextView(context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }
}