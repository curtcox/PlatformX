package se.views.editor;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import se.ref.References;
import x.app.Registry;

import static mach.Mocks._;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ObjectGraphModelTest {

    Object object = new Object();
    References references = new References();
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
        Object ref = new Object();
        references.noteObjectReferences(ref,object);
        model.set(object);
        assertEquals(1,model.getIncomingReferences().length);
        assertSame(ref,model.getIncomingReferences()[0]);
    }

}