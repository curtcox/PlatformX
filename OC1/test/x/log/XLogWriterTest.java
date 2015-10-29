package x.log;

import org.junit.Test;
import x.event.LiveList;

import static org.junit.Assert.*;

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

}