package x.ui;

public final class Rectangle {

    public final int x;
    public final int y;
    public final int w;
    public final int h;
    
    public Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean contains(Point point) {
        return point.x >= x && point.y>=y && point.x < x+w && point.y <y+h;
    }
}
