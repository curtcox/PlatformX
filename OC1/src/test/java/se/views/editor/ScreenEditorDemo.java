package se.views.editor;

import se.util.MutableTaggedValue;
import x.app.Registry;
import x.page.PageLink;
import x.page.PageTags;
import x.uiwidget.XComponent;
import se.events.Events;
import se.ui.EditLinkEvent;
import se.util.TaggedValue;
import se.util.SimpleTaggedValueStringMap;

public class ScreenEditorDemo {

    public static void main(String[] args) {
        loadRegistry();
        addSource();
        events().post(editEvent());
    }

    private static void addSource() {
        SimpleTaggedValueStringMap stringMap = Registry.get(SimpleTaggedValueStringMap.class);
        MutableTaggedValue taggedValue = (MutableTaggedValue) stringMap.newValue(PageTags.of("test"));
        taggedValue.setContents("existing source");
    }

    private static void loadRegistry() {
        Registry.put(SimpleTaggedValueStringMap.class,new SimpleTaggedValueStringMap());
        Registry.put(Events.class,new Events());
        Registry.put(SEScreenEditor.class, SEScreenEditor.of());
    }

    private static Events.Event editEvent() {
        String name = "test";
        PageLink link = PageLink.of(name);
        XComponent layout = null;
        return new EditLinkEvent(link,layout);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}