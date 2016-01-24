package se.views.editor;

final class ObjectGraphModel {

    private Object object;

    void set(Object object) {
        this.object = object;
    }

    public Object get() {
        return object;
    }
}
