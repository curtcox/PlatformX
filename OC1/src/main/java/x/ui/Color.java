package x.ui;

public final class Color {

    public static final Color RED   = new Color(0xff0000);
    public static final Color GREEN = new Color(0x00ff00);
    public static final Color BLUE  = new Color(0x0000ff);
    public static final Color BLACK = new Color(0x000000);
    public static final Color WHITE = new Color(0xffffff);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
}
