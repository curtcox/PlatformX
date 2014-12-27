package common.ui;

public final class UITableLayout
    extends UILayout
{
    public final int rows;
    public final int columns;

    public UITableLayout(int rows, int columns,UIComponent...components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
