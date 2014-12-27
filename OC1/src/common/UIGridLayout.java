package common;

public final class UIGridLayout
    extends UILayout
{
    public final int rows;
    public final int columns;

    public UIGridLayout(int rows, int columns,UIComponent...components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
