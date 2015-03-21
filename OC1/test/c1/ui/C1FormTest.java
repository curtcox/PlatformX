package c1.ui;

import common.screen.ScreenLink;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class C1FormTest {

    @Test
    public void can_create() {
        assertNotNull(new C1Form(ScreenLink.of("")));
    }
}