package se.app;

import common.Registry;
import common.util.StringMap;
import org.junit.Test;
import se.util.SimpleTaggedValueStringMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class RegistryLoaderTest {

    @Test
    public void registry_contains_required_instances_after_loading() {
        RegistryLoader.load();

        assertNotNull(Registry.get(StringMap.class));
        assertNotNull(Registry.get(SimpleTaggedValueStringMap.class));
    }

    @Test
    public void registry_returns_same_instance_for_StringMap_and_TaggedValueStringMap() {
        RegistryLoader.load();

        assertSame(Registry.get(StringMap.class), Registry.get(SimpleTaggedValueStringMap.class));
    }

}