package c1.screen;

import common.Registry;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.util.SimpleStringMap;
import common.util.StringMap;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RootScreenFactoryTest {

    @Test
    public void can_create() {
        StringMap stringMap = new SimpleStringMap(null);
        Registry.put(StringMap.class, stringMap);
        Registry.put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        assertNotNull(RootScreenFactory.of());
    }
}