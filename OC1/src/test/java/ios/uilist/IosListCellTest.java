package ios.uilist;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.uilist.IXListCell;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class IosListCellTest {

    IosListCell testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
        testObject = new IosListCell();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void setFirstRowText_sets_text() {
        String expected = random();

        testObject.apply(new IXListCell.Config(expected));

        assertSame(expected,testObject.getTextLabel().getText());
    }

    private String random() {
        return toString();
    }

}