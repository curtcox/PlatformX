package se.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class SEFormTest {

    @Test
    public void can_create() {
        assertNotNull(new SEForm(""));
    }
}