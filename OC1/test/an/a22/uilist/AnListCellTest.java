package an.a22.uilist;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.uilist.IXListCell;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnListCellTest {

    AnListCell testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Android);
        testObject = new AnListCell();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void setFirstRowText_sets_text() {
        String expected = random();

        testObject.apply(new IXListCell.Config(expected));

        assertSame(expected,testObject.firstRow.getText());
    }

    @Test
    public void setSecondRowText_sets_text() {
        String expected = random();

        testObject.apply(new IXListCell.Config("???",expected,null));

        assertSame(expected,testObject.secondRow.getText());
    }

    private String random() {
        return toString();
    }

}