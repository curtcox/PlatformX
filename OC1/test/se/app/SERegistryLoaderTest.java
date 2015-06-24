package se.app;

import x.Registry;
import x.page.dynamic.TaggedStringSources;
import x.util.StringMap;
import org.junit.Test;
import se.util.TaggedValueStringMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SERegistryLoaderTest {

    @Test
    public void registry_contains_required_instances_after_loading() {
        SERegistryLoader.load();

        assertNotNull(Registry.get(StringMap.class));
        assertNotNull(Registry.get(TaggedValueStringMap.class));
    }

    @Test
    public void registry_returns_same_instance_for_StringMap_and_TaggedValueStringMap() {
        SERegistryLoader.load();

        assertSame(Registry.get(StringMap.class), Registry.get(TaggedValueStringMap.class));
        assertSame(Registry.get(StringMap.class), Registry.get(TaggedStringSources.class));
    }

}