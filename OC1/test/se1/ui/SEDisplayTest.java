package se1.ui;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SEDisplayTest {

    @Test
    public void display_is_a_singleton() {
        assertSame(SEDisplay.of(),SEDisplay.of());
    }
}