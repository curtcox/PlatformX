package se.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class SEFormTest {


    @Test
    public void can_create() {
        assertNotNull(new SEForm(""));
    }

    @Test
    public void title_is_set_from_constructor() {
        String title = toString();
        SEForm testObject = new SEForm(title);

        assertSame(title,testObject.getTitle());
    }
}