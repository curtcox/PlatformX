package common.uiwidget;

public final class UIPeeredComponent
    extends UIComponent
{
    public final Object peer;

    public UIPeeredComponent(Object peer) {
        this.peer = peer;
    }
}
