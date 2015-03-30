package se.editor;

import common.Registry;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import common.uiwidget.UIComponent;
import se.events.Events;
import se.ui.EditCommand;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

public class ScreenEditorDemo {

    public static void main(String[] args) {
        loadRegistry();
        addSource();
        events().post(editEvent());
    }

    private static void addSource() {
        TaggedValueStringMap stringMap = Registry.get(TaggedValueStringMap.class);
        TaggedValue taggedValue = stringMap.newValue();
        taggedValue.setContents("existing source");
        taggedValue.setTags(ScreenTags.of("test"));
    }

    private static void loadRegistry() {
        Registry.put(TaggedValueStringMap.class,new TaggedValueStringMap());
        Registry.put(Events.class,new Events());
        Registry.put(ScreenEditor.class,ScreenEditor.of());
    }

    private static Events.Event editEvent() {
        String name = "test";
        ScreenLink link = ScreenLink.of(name);
        UIComponent layout = null;
        return new EditCommand.Event(link,layout);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}