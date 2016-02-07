package se.events;

import se.views.editor.SEObjectGraphViewer;
import se.views.editor.SETaggedValueEditor;

/**
 * For plugging listeners into the event bus
 */
public final class EventListenerRecruiter {

    public static void recruit() {
        SETaggedValueEditor.of();
        SEObjectGraphViewer.of();
    }
}
