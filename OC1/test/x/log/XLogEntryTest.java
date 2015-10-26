package x.log;

import junit.framework.TestCase;
import org.junit.Test;
import x.event.LiveList;
import x.event.NamedValueListSource;
import x.util.NamedValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class XLogEntryTest {

    Class clazz = getClass();
    String message = toString();
    Object[] details = new Object[] {this};
    XLogEntry entry = new XLogEntry(clazz,message,details);

    @Test
    public void is_NamedValueListSource() {
        assertTrue(entry instanceof NamedValueListSource);
    }

    @Test
    public void asNamedValues_produces_6_named_values() {
        assertEquals(6,entry.asNamedValues().size());
    }

    @Test
    public void clazz_is_named_value_0() {
        NamedValue value = entry.asNamedValues().get(0);
        assertEquals("clazz",value.name);
        assertSame(clazz,value.value);
    }

    @Test
    public void message_is_named_value_1() {
        NamedValue value = entry.asNamedValues().get(1);
        assertEquals("message",value.name);
        assertSame(message,value.value);
    }

    @Test
    public void message_is_named_value_2() {
        NamedValue value = entry.asNamedValues().get(2);
        assertEquals("thread",value.name);
    }

    @Test
    public void message_is_named_value_3() {
        NamedValue value = entry.asNamedValues().get(3);
        assertEquals("screen",value.name);
    }

    @Test
    public void message_is_named_value_4() {
        NamedValue value = entry.asNamedValues().get(4);
        assertEquals("time",value.name);
    }

    @Test
    public void details_is_named_value_5() {
        NamedValue value = entry.asNamedValues().get(5);
        assertEquals("details",value.name);
    }

    @Test
    public void details_is_a_LiveList_that_contains_the_given_details() {
        NamedValue value = entry.asNamedValues().get(5);
        LiveList list = (LiveList) value.value;
        assertEquals(1,list.size());
        assertSame(this,list.get(0));
    }

}