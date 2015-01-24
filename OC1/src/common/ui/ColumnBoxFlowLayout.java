package common.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.*;

public final class ColumnBoxFlowLayout {

    private final int width;
    private Point currentBoxUpperLeft = new Point(0,0);
    private int bottom;
    private List<Rectangle>boxes  = new ArrayList<Rectangle>();

    public ColumnBoxFlowLayout(int width) {
        this.width = width;
    }

    public boolean willFitOnThisLine(Dimension box) {
        return currentBoxUpperLeft.x + box.width <= width;
    }

    public Point startNextLineWith(Dimension box) {
        currentBoxUpperLeft = new Point(0,bottom);
        boxes.add(rectangleForThisLine(box));
        return currentBoxUpperLeft;
    }

    public Point addBoxToThisLine(Dimension box) {
        boxes.add(rectangleForThisLine(box));
        Point upperLeft = currentBoxUpperLeft;
        currentBoxUpperLeft = new Point(box.width,0);
        bottom = box.height;
        return upperLeft;
    }

    private Rectangle rectangleForThisLine(Dimension box) {
        int x = currentBoxUpperLeft.x;
        int y = currentBoxUpperLeft.y;
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
