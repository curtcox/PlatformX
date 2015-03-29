package se.editor;

import common.Registry;
import common.screen.ScreenTags;
import se.events.Events;
import se.ui.EditCommand;
import se.util.SimpleTaggedValue;
import se.util.TaggedValue;

final class ScreenEditor {

    TaggedValue editing = new SimpleTaggedValue();
    private static ScreenEditor screenEditor;

    private ScreenEditor() {}

    public static ScreenEditor of() {
        if (screenEditor == null) {
            screenEditor = new ScreenEditor();
            screenEditor.register();
        }
        return screenEditor;
    }

    void register() {
        events().registerListenerFor(editCommandListener(), EditCommand.Event.class);
    }

    Events.Listener editCommandListener() {
        return new Events.Listener() {
            @Override
            public void onEvent(Events.Event event) {
                EditCommand.Event editEvent = (EditCommand.Event) event;
                edit(taggedValue(editEvent));
            }
        };
    }


    void edit(ScreenTags tags) {
        editing.setTags(tags);
    }

    private static ScreenTags taggedValue(EditCommand.Event editEvent) {
        return editEvent.link.tags;
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}
