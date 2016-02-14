package se.views.editor;

import se.ref.References;
import x.app.Registry;

final class ObjectGraphModel {

    private Object object;

    void set(Object object) {
        this.object = object;
    }

    public Object get() {
        return object;
    }

    public Class getTargetClass() {
        return object.getClass();
    }

    public Object[] getIncomingReferences() {
        return references().to(object);
    }

    public Object[] getOutgoingReferences() {
        return null;
    }

    References references() {
        return References.of();
    }
}
