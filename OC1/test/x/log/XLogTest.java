package x.log;

import junit.framework.TestCase;
import org.junit.Test;
import org.robovm.objc.ObjCClass;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class XLogTest {

    @Test
    public void is_a_log() {
        assertTrue(XLog.of(Object.class) instanceof ILog);
    }

    @Test
    public void target_is_the_target_from_the_factory() {
        Object target = new Object();
        assertSame(target,XLog.of(target).target);
    }

    @Test
    public void clazz_is_the_same_as_the_target_when_the_target_is_a_class() {
        assertSame(Object.class,XLog.of(Object.class).clazz);
        assertSame(String.class,XLog.of(String.class).clazz);
        assertSame(List.class,  XLog.of(List.class).clazz);
    }

}