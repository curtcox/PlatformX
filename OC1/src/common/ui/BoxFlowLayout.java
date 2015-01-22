package common.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.*;

public final class BoxFlowLayout {

    private final Dimension size;
    private Point at = new Point(0,0);
    private List<Rectangle>boxes  = new ArrayList<Rectangle>();

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
        boxes.add(rectangleFor(box));
        Point upperLeft = at;
        at = new Point(box.width,0);
        return upperLeft;
    }

    private Rectangle rectangleFor(Dimension box) {
        int x = at.x;
        int y = at.y;
        int w = box.width;
        int h = box.height;
        return new Rectangle(x,y,w,h);
    }

    public int getPointIndex(Point point) {
        int i = 0;
        for (Rectangle box : boxes) {
            if (box.contains(point)) {
                return i;
            }
            i++;
        }
        return -1;
    }

}
