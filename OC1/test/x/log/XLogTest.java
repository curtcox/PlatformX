package x.log;

import junit.framework.TestCase;
import org.junit.Test;
import org.robovm.objc.ObjCClass;

import static org.junit.Assert.assertTrue;

public class XLogTest {

    @Test
    public void is_a_log() {
        assertTrue(new XLog(Object.class) instanceof ILog);
    }
}