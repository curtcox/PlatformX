package oc1.ui;


public final class BorderContainer {

    Object center;
    Object north;
    Object east;

    public BorderContainer(Object component) {
        center = component;
    }

    public BorderContainer addNorth(Object component) {
        north = component;
        return this;
    }

    public BorderContainer addEast(Object component) {
        east = component;
        return this;
    }
}
