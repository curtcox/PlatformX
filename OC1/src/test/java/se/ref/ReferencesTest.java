package se.ref;

import org.junit.Test;
import x.app.Registry;

import static org.junit.Assert.*;

public class ReferencesTest {

    Object object = new Object();
    References references = References.of();

    @Test
    public void can_create() {
        assertNotNull(References.of());
    }

    @Test
    public void to_object_returns_empty_array_when_no_incoming_references() {
        Object[] to = references.to(object);
        assertEquals(0,to.length);
    }

    @Test
    public void to_object_returns_empty_array_when_incoming_references_to_a_different_object() {
        Object referencing = new Object();
        references.noteObjectReferences(referencing,new Object());
        Object[] to = references.to(object);
        assertEquals(0,to.length);
    }

    @Test
    public void to_object_returns_array_with_referencing_object_when_there_is_one() {
        Object referencing = new Object();
        references.noteObjectReferences(referencing,object);
        Object[] to = references.to(object);
        assertEquals(1,to.length);
        assertSame(referencing,to[0]);
    }

    @Test
    public void of_returns_instance() {
        assertTrue(References.of() instanceof References);
    }

    @Test
    public void from_object_returns_empty_array_when_no_outgoing_references() {
        Object[] from = references.from(object);
        assertEquals(0,from.length);
    }

    @Test
    public void from_object_returns_empty_array_when_outgoing_references_from_a_different_object() {
        references.noteObjectReferences(new Object(),new Object());
        Object[] from = references.from(object);
        assertEquals(0,from.length);
    }

    @Test
    public void from_object_returns_array_with_referencing_object_when_there_is_one() {
        Object referenced = new Object();
        references.noteObjectReferences(object,referenced);
        Object[] from = references.from(object);
        assertEquals(1,from.length);
        assertSame(referenced,from[0]);
    }

}