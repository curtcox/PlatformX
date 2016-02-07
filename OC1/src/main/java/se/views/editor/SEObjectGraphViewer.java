package se.views.editor;

import se.events.Events;
import se.events.ViewObjectEvent;
import se.frame.FrameMeta;
import se.frame.SEFrame;
import se.ui.SEBorderContainer;
import x.app.Registry;

import java.awt.*;

public final class SEObjectGraphViewer
    implements Events.Listener
{

    static SEObjectGraphViewer viewer;
    ObjectGraphModel model = new ObjectGraphModel();
    final SEFrame frame;
    final SEObjectLabel targetLabel = SEObjectLabel.of("Page");

    SEObjectGraphViewer() {
        frame = new SEFrame(frameMeta());
    }

    public static SEObjectGraphViewer of() {
        if (viewer == null) {
            viewer = new SEObjectGraphViewer();
            viewer.register();
            viewer.init();
        }
        return viewer;
    }

    private static FrameMeta frameMeta() {
        return new FrameMeta(
                "For examining how an object relates to other objects.",
                "Select objects of interest.",
                SEObjectGraphViewer.class
        );
    }

    void init() {
        frame.pack();
    }

    void register() {
        events().registerListenerFor(this, ViewObjectEvent.class);
    }

    @Override
    public void onEvent(Events.Event event) {
        onViewObjectEvent((ViewObjectEvent) event);
    }

    private void onViewObjectEvent(ViewObjectEvent event) {
        view(event.object);
    }

    Events events() {
        return Registry.get(Events.class);
    }

    void view(Object target) {
        model.set(target);
        targetLabel.set(target);
        frame.setVisible(true);
        System.out.println("???");
    }
}
