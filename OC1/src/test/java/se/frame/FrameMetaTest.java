package se.frame;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import se.views.editor.SEScreenEditor;
import x.app.Registry;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class FrameMetaTest {

    public JavaSourceCodeLookup sourceCodeLookup;

    @Before
    public void setUp() {
        Mocks.init(this);
        _("Object source"); sourceCodeLookup.sourceFor(Object.class);
        _("String source"); sourceCodeLookup.sourceFor(String.class);
        _("Editor source"); sourceCodeLookup.sourceFor(SEScreenEditor.class);
        Registry.put(JavaSourceCodeLookup.class,sourceCodeLookup);
    }

    @Test
    public void can_create() {
        assertNotNull(new FrameMeta("","",Object.class));
    }

    @Test
    public void why_is_set_from_constructor() {
        String why = toString();
        FrameMeta meta = new FrameMeta(why,"",Object.class);
        assertSame(why,meta.what_its_for);
    }

    @Test
    public void usage_is_set_from_constructor() {
        String usage = toString();
        FrameMeta meta = new FrameMeta("",usage,Object.class);
        assertSame(usage,meta.how_to_use_it);
    }

    @Test
    public void location_is_set_from_constructor_as_class_name() {
        assertEquals("java.lang.Object",new FrameMeta("","",Object.class).source_code_location);
        assertEquals("java.lang.String",new FrameMeta("","",String.class).source_code_location);
        assertEquals("se.views.editor.SEScreenEditor",new FrameMeta("","",SEScreenEditor.class).source_code_location);
    }

    @Test
    public void code_is_set_from_constructor_class_and_uses_JavaSourceCodeLookup() {
        String source = toString();
        _(source); sourceCodeLookup.sourceFor(String.class);

        FrameMeta meta = new FrameMeta("","",String.class);

        assertSame(source,meta.source_code);
    }
}