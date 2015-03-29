package se.editor;

import common.Registry;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;
import se.ui.EditCommand;

public class ScreenEditorDemo {

    public static void main(String[] args) {
        loadRegistry();
        events().post(editEvent());
    }

    private static void loadRegistry() {
        Registry.put(Events.class,new Events());
    }

    private static Events.Event editEvent() {
        String name = "screen name";
        ScreenLink link = ScreenLink.of(name);
        UIComponent layout = null;
        return new EditCommand.Event(link,layout);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}