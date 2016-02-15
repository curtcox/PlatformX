package se.app;

import config.ShouldRun;
import org.junit.Before;
import se.frame.JavaSourceCodeLookup;
import x.app.CurrentState;
import x.app.Registry;
import x.page.dynamic.TaggedStringSources;
import x.ref.XReferences;
import x.services.XGeocoder;
import x.uilist.XListContentInstaller;
import x.util.StringMap;
import org.junit.Test;
import se.util.TaggedValueStringMap;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SERegistryLoaderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE_UI);
    }

    @Test
    public void registry_contains_required_instances_after_loading() {
        SERegistryLoader.load();

        assertNotNull(Registry.get(StringMap.class));
        assertNotNull(Registry.get(TaggedValueStringMap.class));
        assertNotNull(Registry.get(XListContentInstaller.class));
        assertNotNull(Registry.get(CurrentState.class));
        assertNotNull(Registry.get(XGeocoder.class));
        assertNotNull(Registry.get(JavaSourceCodeLookup.class));
        assertNotNull(Registry.get(XReferences.class));
    }

    @Test
    public void registry_returns_same_instance_for_StringMap_and_TaggedValueStringMap() {
        SERegistryLoader.load();

        assertSame(Registry.get(StringMap.class), Registry.get(TaggedValueStringMap.class));
        assertSame(Registry.get(StringMap.class), Registry.get(TaggedStringSources.class));
    }

}