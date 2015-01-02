package common.ui;

public final class UIGrid
    extends UILayout
{
    public final int rows;
    public final int columns;

    public UIGrid(int rows, int columns, UIComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
