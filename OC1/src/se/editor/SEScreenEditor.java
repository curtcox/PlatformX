package se.editor;

import se.events.Events;
import se.ui.EditTaggedValueEvent;
import se.ui.SEBorderContainer;
import se.uiwidget.StringEditor;
import se.util.TaggedValue;
import x.app.Registry;
import x.event.StringChange;
import x.page.Page;
import x.screen.Screen;
import x.uiwidget.XComponent;

import javax.swing.*;
import java.awt.*;

public final class SEScreenEditor {

    TaggedValue editing;
    Page page;
    XComponent layout;
    final JFrame frame = new JFrame();
    final JLabel pageLabel = new JLabel();
    final JLabel layoutLabel = new JLabel();
    final StringEditor editor = new StringEditor(textListener(),null);

    private static SEScreenEditor screenEditor;

    /**
     * Only use this for testing.
     */
    SEScreenEditor() {}

    public static SEScreenEditor of() {
        if (screenEditor == null) {
            screenEditor = new SEScreenEditor();
            screenEditor.register();
            screenEditor.init();
        }
        return screenEditor;
    }

    void init() {
        Container contents = frame.getContentPane();
        contents.add(SEBorderContainer.of(editor).north(toolbar()));
        frame.pack();
    }

    private JComponent toolbar() {
        return SEBorderContainer.of(pageLabel).south(layoutLabel);
    }

    void register() {
        events().registerListenerFor(editCommandListener(), EditTaggedValueEvent.class);
    }

    Events.Listener editCommandListener() {
        return new Events.Listener() {
            @Override
            public void onEvent(Events.Event event) {
                EditTaggedValueEvent editEvent = (EditTaggedValueEvent) event;
                edit(editEvent.taggedValue);
                setPage(editEvent.page);
                setLayout(editEvent.layout);
            }
        };
    }

    private StringChange.Listener textListener() {
        return new StringChange.Listener() {
            @Override
            public void onChange(StringChange.Event event) {
                editing.setContents(editor.getText());
                refreshScreen();
            }
        };
    }

    private void refreshScreen() {
        Screen screen = Screen.getShowing();
        if (screen!=null) {
            screen.refresh();
        }
    }

    void setPage(Page page) {
        this.page = page;
        pageLabel.setText(page.toString());
    }

    void setLayout(XComponent layout) {
        this.layout = layout;
        layoutLabel.setText(layout.toString());
    }

    void edit(TaggedValue value) {
        editing = value;
        editor.setText(value.getContents());
        frame.setTitle(value.getTags().toString());
        frame.setVisible(true);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}