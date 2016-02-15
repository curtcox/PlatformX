package x.log;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.event.LiveList;
import x.ref.XReferences;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static org.junit.Assert.*;

public class XLogWriterTest {

    XReferences references;
    XLogWriter writer = new XLogWriter();

    @Before
    public void setUp() {
        Mocks.init(this);
        Registry.put(XReferences.class,references);
    }

    @Test
    public void is_a_LiveListSource() {
        assertTrue(writer instanceof LiveList.Source);
    }

    @Test
    public void log_is_empty_when_nothing_has_been_published() {
        assertTrue(writer.log.isEmpty());
    }

    @Test
    public void log_is_not_empty_when_something_has_been_published() {
        writer.log(null,Object.class,"");
        assertFalse(writer.log.isEmpty());
    }

    @Test
    public void published_log_entry_matches_what_was_logged() {
        Class clazz = getClass();
        Object target = new Object();
        String message = toString();

        writer.log(target,clazz,message,this);

        XLogEntry entry = writer.log().get(0);
        assertSame(target,entry.target);
        assertSame(clazz,entry.clazz);
        assertSame(message,entry.message);
        assertSame(this,entry.details[0]);
    }

    @Test
    public void log_notes_parameters_with_References() {
        Class clazz = getClass();
        Object target = new Object();
        String message = toString();
        Object[] details = new Object[0];

        writer.log(target,clazz,message,details);

        verify();
        references.noteObject(target);
        references.noteObject(details);
    }
}