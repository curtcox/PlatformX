package se.editor;

import common.Registry;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import common.uiwidget.UIComponent;
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
        TaggedValue taggedValue = stringMap.newValue();
        taggedValue.setContents("existing source");
        taggedValue.setTags(ScreenTags.of("test"));
    }

    private static void loadRegistry() {
        Registry.put(SimpleTaggedValueStringMap.class,new SimpleTaggedValueStringMap());
        Registry.put(Events.class,new Events());
        Registry.put(ScreenEditor.class,ScreenEditor.of());
    }

    private static Events.Event editEvent() {
        String name = "test";
        ScreenLink link = ScreenLink.of(name);
        UIComponent layout = null;
        return new EditLinkEvent(link,layout);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}