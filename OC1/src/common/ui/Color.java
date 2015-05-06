package common.ui;

public final class Color {

    public static final Color RED = null;
    public static final Color BLUE = null;
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
