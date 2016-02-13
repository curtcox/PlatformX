package se.ref;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReferencesTest {

    Object object = new Object();
    References references = new References();

    @Test
    public void can_create() {
        assertNotNull(new References());
    }

    @Test
    public void to_object_returns_empty_array_when_no_incoming_references() {
        Object[] to = references.to(object);
        assertEquals(0,to.length);
    }

    @Test
    public void to_object_returns_empty_array_when_incoming_references_to_a_different_object() {
        Object ref = new Object();
        references.noteObjectReferences(ref,new Object());
        Object[] to = references.to(object);
        assertEquals(0,to.length);
    }

    @Test
    public void to_object_returns_array_with_referencing_object_when_there_is_one() {
        Object ref = new Object();
        references.noteObjectReferences(ref,object);
        Object[] to = references.to(object);
        assertEquals(1,to.length);
        assertSame(ref,to[0]);
    }

}