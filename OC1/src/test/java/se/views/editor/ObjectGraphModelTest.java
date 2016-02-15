package se.views.editor;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import se.ref.References;
import x.app.Registry;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ObjectGraphModelTest {

    Object object = new Object();
    Referencing referencing = new Referencing();
    References references = References.of();
    static class Referencing {
        Object referenced;
    }
    ObjectGraphModel model = new ObjectGraphModel();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        Registry.put(References.class,references);
    }

    @Test
    public void get_returns_object_set() {
        model.set(object);
        assertSame(object,model.get());
    }

    @Test
    public void getTargetClass_returns_target_class() {
        model.set(object);
        assertSame(object.getClass(),model.getTargetClass());
    }

    @Test
    public void getIncomingReferences_returns_no_Referencing_objects_when_there_are_none() {
        model.set(object);
        assertEquals(0,model.getIncomingReferences().length);
    }

    @Test
    public void getIncomingReferences_returns_Referencing_object_when_there_is_one() {
        referencing.referenced = object;
        references.noteObject(referencing);
        model.set(object);
        assertEquals(1,model.getIncomingReferences().length);
        assertSame(referencing,model.getIncomingReferences()[0]);
    }

    @Test
    public void getOutgoingReferences_returns_no_Referencing_objects_when_there_are_none() {
        model.set(object);
        assertEquals(0,model.getOutgoingReferences().length);
    }

    @Test
    public void getOutgoingReferences_returns_Referencing_object_when_there_is_one() {
        referencing.referenced = object;
        references.noteObject(referencing);
        model.set(referencing);
        assertEquals(1,model.getOutgoingReferences().length);
        assertSame(object,model.getOutgoingReferences()[0]);
    }

}