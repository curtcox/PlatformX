package se.views.editor;

import config.ShouldRun;
import se.frame.JavaSourceCodeLookup;
import se.frame.SEJavaSourceCodeLookup;
import se.util.MutableTaggedValue;
import x.app.Registry;
import x.page.Page;
import x.page.PageLink;
import x.page.PageTags;
import x.screen.Screen;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;
import fake.FakeFormFactory;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.events.EditTaggedValueEvent;
import se.util.SimpleTaggedValueStringMap;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SETaggedValueEditorTest {

    Events events = new Events();
    SimpleTaggedValueStringMap stringMap = new SimpleTaggedValueStringMap();
    SETaggedValueEditor testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE_UI);
        FakeSERegistryLoader.load();
        Registry.put(Events.class,events);
        Registry.put(SimpleTaggedValueStringMap.class,stringMap);
        Registry.put(IFormFactory.class,new FakeFormFactory());
        Registry.put(JavaSourceCodeLookup.class,new SEJavaSourceCodeLookup());
        testObject = new SETaggedValueEditor();
    }

    @Test
    public void can_create() {
        assertNotNull(SETaggedValueEditor.of());
    }

    @Test
    public void is_singleton() {
        assertSame(SETaggedValueEditor.of(), SETaggedValueEditor.of());
    }

    @Test
    public void sending_an_edit_command_event_causes_a_value_with_the_corresponding_tags_to_be_edited_when_registered() {
        PageTags tags = PageTags.of("whatever");
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(tags);
        XComponent layout = new XComponent();
        Page page = Page.withFixedLayout("title",layout);

        post(new EditTaggedValueEvent(value,page,layout));

        assertEquals(value.getTags(), testObject.editing.getTags());
    }

    @Test
    public void edit_makes_the_editing_frame_visible() {
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(PageTags.of("use_for_title"));

        testObject.edit(value);

        assertTrue(testObject.frame.isVisible());
    }

    @Test
    public void edit_puts_the_edited_text_in_the_editor() {
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(PageTags.of("use_for_title"));
        value.setContents("contents");
        screen().show();

        testObject.edit(value);

        assertEquals("contents", testObject.valueString.getText());
    }

    @Test
    public void says_it_is_for_viewing_and_editing() {
        assertEquals(
            "For viewing and editing application source code.",
            testObject.frame.meta.what_its_for);
    }

    @Test
    public void displays_page_after_EditTaggedValueEvent_is_posted() {
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(PageTags.of("whatever"));
        XComponent layout = new XComponent();
        Page page = Page.withFixedLayout("title",layout);

        post(new EditTaggedValueEvent(value,page,layout));

        assertSame(page,testObject.page);
        assertEquals("Page=" + page.toString(),testObject.pageLabel.getText());
    }

    @Test
    public void displays_layout_after_EditTaggedValueEvent_is_posted() {
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(PageTags.of("whatever"));
        XComponent layout = new XComponent();
        Page page = Page.withFixedLayout("title",layout);

        post(new EditTaggedValueEvent(value,page,layout));

        assertSame(layout,testObject.layout);
        assertEquals("Layout=" + layout.toString(),testObject.layoutLabel.getText());
    }

    @Test
    public void edits_value_contents_after_EditTaggedValueEvent_is_posted() {
        MutableTaggedValue value = (MutableTaggedValue) stringMap.newValue(PageTags.of("whatever"));
        String contents = toString();
        value.setContents(contents);
        XComponent layout = new XComponent();
        Page page = Page.withFixedLayout("title",layout);

        post(new EditTaggedValueEvent(value,page,layout));

        assertSame(value,testObject.editing);
        assertEquals(contents,testObject.valueString.getText());
    }

    void post(EditTaggedValueEvent event) {
        testObject.register();
        events.post(event);
    }

    Screen screen() {
        PageLink link = PageLink.of("");
        Page page = new Page(link) {
            @Override
            public XComponent layoutForPortrait() {
                return null;
            }
        };
        return Screen.of(page);
    }
}