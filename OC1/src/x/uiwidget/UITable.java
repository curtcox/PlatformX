package x.uiwidget;

public final class UITable
    extends UIContainer
{
    public final int rows;
    public final int columns;

    public UITable(int rows, int columns, UIComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
