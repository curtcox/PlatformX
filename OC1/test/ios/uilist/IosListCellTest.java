package ios.uilist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class IosListCellTest {

    IosListCell testObject;

    @Before
    public void setUp() {
        testObject = new IosListCell();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void setFirstRowText_sets_text() {
        String expected = random();

        testObject.setFirstRowText(expected);

        assertSame(expected,testObject.firstRow.getText());
    }

    @Test
    public void setSecondRowText_sets_text() {
        String expected = random();

        testObject.setSecondRowText(expected);

        assertSame(expected,testObject.secondRow.getText());
    }

    private String random() {
        return toString();
    }

}