package common.ui;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void toInt() {
        assertEquals(0x000000,Color.BLACK.toInt());
        assertEquals(0xffffff, Color.WHITE.toInt());
    }
}