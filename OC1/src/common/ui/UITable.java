package common.ui;

public final class UITable
    extends UILayout
{
    public final int rows;
    public final int columns;

    public UITable(int rows, int columns, UIComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
