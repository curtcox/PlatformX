package x.uiwidget;

import x.app.CurrentState;
import x.event.Change;
import x.event.StringSource;
import x.ui.TextPosition;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementers must provide onTap.
 */
public abstract class UIButton
    extends UIComponent
{
    public String icon;
    public String text;

    final String name;
    TextPosition textPosition;

    public UIButton(final String name) {
        this.name = name;
        this.text = name;
    }

    /**
     * Override this method to perform the button tap action.
     */
    public abstract void onTap();

    public void updateTextOnChange(Change.Source change,final StringSource source) {
        change.addListener(new Change.Listener() {
            public void onChange() {
                text = source.getString();
            }
        });
    }

    public void updateTextOnChange(final StringSource source) {
        CurrentState.get().addListener(new Change.Listener(){
            public void onChange() {
                text = source.getString();
            }
        });
    }

    @Override
    public String toString() {
        return "Button:" + name;
    }

    public void setTextPosition(TextPosition textPosition) {
        this.textPosition = textPosition;
    }

    public String getText() {
        return text;
    }

}
