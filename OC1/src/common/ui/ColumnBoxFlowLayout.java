package common.ui;

import java.util.*;

public final class ColumnBoxFlowLayout {

    private final int width;
    private int bottom;
    private int lineHeight;
    private int lineWidth;
    private List<Rectangle>boxes  = new ArrayList<Rectangle>();

    public ColumnBoxFlowLayout(int width) {
        this.width = width;
    }

    public boolean willFitOnThisLine(Dimension box) {
        return lineWidth + box.width <= width;
    }

    public Point startNextLineWith(Dimension box) {
        bottom += lineHeight;
        Point point = new Point(0,bottom);
        lineHeight = box.height;
        lineWidth = box.width;
        boxes.add(rectangleForBoxAt(box,point));
        return point;
    }

    public Point addBoxToThisLine(Dimension box) {
        Point point = new Point(lineWidth,bottom);
        boxes.add(rectangleForBoxAt(box, point));
        lineWidth += box.width;
        lineHeight = Math.max(lineHeight,box.height);
        return point;
    }

    private Rectangle rectangleForBoxAt(Dimension box, Point at) {
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
