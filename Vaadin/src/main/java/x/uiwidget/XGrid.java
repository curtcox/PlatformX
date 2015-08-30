package x.uiwidget;

public final class XGrid
    extends XContainer
{
    public final int rows;
    public final int columns;

    public XGrid(int rows, int columns, XComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
