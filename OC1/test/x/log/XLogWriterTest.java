package x.log;

import junit.framework.TestCase;
import org.junit.Test;
import x.event.LiveList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class XLogWriterTest {

    XLogWriter writer = new XLogWriter();

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
        writer.log(Object.class,"");
        assertFalse(writer.log.isEmpty());
    }

    @Test
    public void published_log_entry_matches_what_was_logged() {
        Class clazz = getClass();
        String message = toString();
        writer.log(clazz,message,this);
        XLogEntry entry = writer.log().get(0);
        assertSame(clazz,entry.clazz);
        assertSame(message,entry.message);
        assertSame(this,entry.details[0]);
    }

}