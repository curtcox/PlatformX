package common.ui;

import common.app.CurrentState;
import common.event.Change;
import common.event.StringSource;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementers must provide onTap.
 * @author Curt
 */
public abstract class UIButton
    extends UIComponent
{
    public String icon;
    public String text;

    final String name;
    int textPosition;

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

    public void setTextPosition(int textPosition) {
        this.textPosition = textPosition;
    }

    public String getText() {
        return text;
    }

}
