package se.views.editor;

import se.events.Events;
import se.frame.FrameMeta;
import se.frame.SEFrame;
import se.events.EditTaggedValueEvent;
import se.ui.SEBorderContainer;
import se.uiwidget.StringEditor;
import se.util.MutableTaggedValue;
import x.app.Registry;
import x.event.StringChange;
import x.page.Page;
import x.screen.Screen;
import x.uiwidget.XComponent;

import javax.swing.*;
import java.awt.*;

public final class SETaggedValueEditor {

    MutableTaggedValue editing;
    Page page;
    XComponent layout;
    final SEFrame frame = new SEFrame(frameMeta());
    final SEObjectReferencePanel pageLabel = SEObjectReferencePanel.oneLine("Page");
    final SEObjectReferencePanel layoutLabel = SEObjectReferencePanel.oneLine("Layout");
    final StringEditor valueString = new StringEditor(textListener(),null);

    private static SETaggedValueEditor screenEditor;

    /**
     * Only use this for testing.
     */
    SETaggedValueEditor() {}

    public static SETaggedValueEditor of() {
        if (screenEditor == null) {
            screenEditor = new SETaggedValueEditor();
            screenEditor.register();
            screenEditor.init();
        }
        return screenEditor;
    }

    private static FrameMeta frameMeta() {
        return new FrameMeta(
                "For viewing and editing application source code.",
                "Inspect and edit the text shown.",
                SETaggedValueEditor.class
        );
    }

    void init() {
        Container contents = frame.getContentPane();
        contents.add(SEBorderContainer.of(valueString).north(toolbar()));
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
                onEditTaggedValueEvent((EditTaggedValueEvent) event);
            }
        };
    }

    private void onEditTaggedValueEvent(EditTaggedValueEvent editEvent) {
        edit(editEvent.taggedValue);
        setPage(editEvent.page);
        setLayout(editEvent.layout);
    }

    private StringChange.Listener textListener() {
        return new StringChange.Listener() {
            @Override
            public void onChange(StringChange.Event event) {
                MutableTaggedValue editableContent = editing;
                editableContent.setContents(valueString.getText());
                refreshScreen();
            }
        };
    }

    private void refreshScreen() {
        Screen screen = Screen.getShowing();
        if (screen!=null) {
            screen.refresh();
            frame.pack();
        }
    }

    void setPage(Page page) {
        this.page = page;
        pageLabel.set(page);
    }

    void setLayout(XComponent layout) {
        this.layout = layout;
        layoutLabel.set(layout);
        frame.pack();
    }

    void edit(MutableTaggedValue value) {
        editing = value;
        valueString.setText(value.getContents());
        frame.setVisible(true);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}
