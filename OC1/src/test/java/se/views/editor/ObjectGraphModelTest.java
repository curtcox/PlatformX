package se.views.editor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectGraphModelTest {

    Object object = new Object();
    ObjectGraphModel model = new ObjectGraphModel();

    @Test
    public void get_returns_object_set() {
        model.set(object);
        assertSame(object,model.get());
    }

}