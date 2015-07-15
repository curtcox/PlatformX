package x.uiwidget;

public final class XTable
    extends XContainer
{
    public final int rows;
    public final int columns;

    public XTable(int rows, int columns, XComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
