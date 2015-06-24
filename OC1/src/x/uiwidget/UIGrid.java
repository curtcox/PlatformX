package x.uiwidget;

public final class UIGrid
    extends UIContainer
{
    public final int rows;
    public final int columns;

    public UIGrid(int rows, int columns, UIComponent... components) {
        super(components);
        this.rows = rows;
        this.columns = columns;
    }
}
