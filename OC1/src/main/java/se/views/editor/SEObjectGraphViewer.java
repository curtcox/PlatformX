package se.views.editor;

import se.events.Events;
import se.events.ViewObjectEvent;
import x.app.Registry;

public class SEObjectGraphViewer {

    static final SEObjectGraphViewer viewer = new SEObjectGraphViewer();
    ObjectGraphModel model = new ObjectGraphModel();

    SEObjectGraphViewer() {}

    public static SEObjectGraphViewer of() {
        return viewer;
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
