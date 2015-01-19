package se.uiwidget;

import java.awt.*;

public final class BoxFlowLayout {

    private final Dimension size;
    private Dimension added;

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
    public Point addBoxToThisLine(Dimension box) {
        added = box;
        return new Point(0,0);
    }

    public int getPointIndex(Point point) {
        return added!=null && point.x <= added.width && point.y <= added.height ? 0 : -1;
    }
}
