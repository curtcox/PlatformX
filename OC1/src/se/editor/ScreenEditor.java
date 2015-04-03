package se.editor;

import common.Registry;
import common.event.StringChange;
import common.screen.Screen;
import common.screen.ScreenTags;
import se.events.Events;
import se.ui.EditCommand;
import se.uiwidget.StringEditor;
import se.util.SimpleTaggedValue;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

import javax.swing.*;

public final class ScreenEditor {

    TaggedValue editing = new SimpleTaggedValue();
    private static ScreenEditor screenEditor;
    final JFrame frame = new JFrame();
    final StringEditor editor = new StringEditor(textListener(),null);

    ScreenEditor() {
        frame.getContentPane().add(editor);
        frame.setSize(500,500);
    }

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

    private StringChange.Listener textListener() {
        return new StringChange.Listener() {
            @Override
            public void onChange(StringChange.Event event) {
                editing.setContents(editor.getText());
                Screen.getShowing().refresh();
            }
        };
    }

    void edit(ScreenTags tags) {
        editing.setTags(tags);
        editor.setText(stringMap().get(tags.toString()));
        frame.setVisible(true);
    }

    private static ScreenTags taggedValue(EditCommand.Event editEvent) {
        return editEvent.link.tags;
    }

    static Events events() {
        return Registry.get(Events.class);
    }

    static TaggedValueStringMap stringMap() {
        return Registry.get(TaggedValueStringMap.class);
    }
}
