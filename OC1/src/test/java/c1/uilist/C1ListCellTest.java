package c1.uilist;

import config.ShouldRun;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;
import x.uilist.IXListCell;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class C1ListCellTest  {

    C1ListCell testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeUIManager.of();
        testObject = new C1ListCell();
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

        testObject.apply(new IXListCell.Config("?",expected,null));

        assertSame(expected,testObject.secondRow.getText());
    }

    private String random() {
        return toString();
    }

}