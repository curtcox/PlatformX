package se.ref;

import org.junit.Test;
import x.app.Registry;

import static org.junit.Assert.*;

public class ReferencesTest {

    Object object = new Object();
    Referencing referencing = new Referencing();
    References references = References.of();
    static class Referencing {
        Object referenced;
    }

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
        referencing.referenced = new Object();
        references.noteObject(referencing);
        Object[] to = references.to(object);
        assertEquals(0,to.length);
    }

    @Test
    public void to_object_returns_array_with_referencing_object_when_there_is_one() {
        referencing.referenced = object;
        references.noteObject(referencing);
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
        referencing.referenced = new Object();
        references.noteObject(referencing);
        Object[] from = references.from(object);
        assertEquals(0,from.length);
    }

    @Test
    public void from_object_returns_array_with_referencing_object_when_there_is_one() {
        referencing.referenced = object;
        references.noteObject(referencing);
        Object[] from = references.from(referencing);
        assertEquals(1,from.length);
        assertSame(object,from[0]);
    }

}