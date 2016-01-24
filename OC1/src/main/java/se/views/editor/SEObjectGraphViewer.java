package se.views.editor;

import se.events.Events;
import se.events.ViewObjectEvent;
import se.frame.FrameMeta;
import se.frame.SEFrame;
import x.app.Registry;

public class SEObjectGraphViewer {

    static final SEObjectGraphViewer viewer = new SEObjectGraphViewer();
    ObjectGraphModel model = new ObjectGraphModel();
    final SEFrame frame;

    SEObjectGraphViewer() {
        frame = new SEFrame(frameMeta());
    }

    public static SEObjectGraphViewer of() {
        return viewer;
    }

    private static FrameMeta frameMeta() {
        return new FrameMeta(
                "For examining how an object relates to other objects.",
                "Select objects of interest.",
                SEObjectGraphViewer.class
        );
    }

    void register() {
        events().registerListenerFor(listener(), ViewObjectEvent.class);
    }

    private Events.Listener listener() {
        return new Events.Listener() {
            @Override
            public void onEvent(Events.Event event) {
                ViewObjectEvent viewObjectEvent = (ViewObjectEvent) event;
                model.set(viewObjectEvent.object);
            }
        };
    }

    Events events() {
        return Registry.get(Events.class);
    }
}
