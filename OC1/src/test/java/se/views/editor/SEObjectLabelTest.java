package se.views.editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SEObjectLabelTest {

    SEObjectLabel label = SEObjectLabel.twoLine("");

    @Test
    public void get_returns_value_that_was_set() {
        Object target = new Object();
        label.set(target);
        assertSame(target,label.get());
    }
}