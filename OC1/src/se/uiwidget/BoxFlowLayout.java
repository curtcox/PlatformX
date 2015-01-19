package se.uiwidget;

import java.awt.*;

public final class BoxFlowLayout {

    private final Dimension size;
    private boolean added;

    public BoxFlowLayout(Dimension size) {
        this.size = size;
    }

//    public boolean willFitOnALine(Dimension size) {
//        return false;
//    }

    public boolean willFitOnThisLine(Dimension size) {
        return size.width <= this.size.width && size.height<=this.size.height;
    }

//    public Point startNextLineWith(Dimension size) {
//        return null;
//    }
//
    public Point addBoxToThisLine(Dimension size) {
        added = true;
        return null;
    }

    public int getPointIndex(Point point) {
        return added ? 0 : -1;
    }
}
