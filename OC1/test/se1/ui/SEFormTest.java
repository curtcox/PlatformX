package se1.ui;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class SEFormTest {

    @Test
    public void can_create() {
        assertNotNull(new SEForm(""));
    }
}