package se.editor;

import common.Registry;
import se.events.Events;
import se.ui.EditCommand;
import se.util.TaggedValueStringMap;

final class ScreenEditor {

    private TaggedValueStringMap.TaggedValue value;
    private static ScreenEditor editor;

    static ScreenEditor of() {
        return editor;
    }

    static void register() {
        events().registerListenerFor(editCommandListener(),EditCommand.Event.class);
    }

    static Events.Listener editCommandListener() {
        return new Events.Listener() {
            @Override
            public void onEvent(Events.Event event) {
                EditCommand.Event editEvent = (EditCommand.Event) event;
                editor().edit(taggedValue(editEvent));
            }
        };
    }

    private void edit(TaggedValueStringMap.TaggedValue taggedValue) {

    }

    private static TaggedValueStringMap.TaggedValue taggedValue(EditCommand.Event editEvent) {
        return null;
    }

    private static ScreenEditor editor() {
        return null;
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}
