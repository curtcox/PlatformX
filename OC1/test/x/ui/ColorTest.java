package x.ui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void toInt() {
        assertEquals(0x000000,Color.BLACK.toInt());
        assertEquals(0xffffff, Color.WHITE.toInt());
        assertEquals(0xff0000, Color.RED.toInt());
        assertEquals(0x00ff00, Color.GREEN.toInt());
        assertEquals(0x0000ff, Color.BLUE.toInt());
    }
}