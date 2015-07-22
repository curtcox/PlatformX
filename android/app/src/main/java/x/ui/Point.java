package x.ui;

public final class Point {
    
    public final int x;
    public final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        Point that = (Point) o;
        return x==that.x && y==that.y;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
