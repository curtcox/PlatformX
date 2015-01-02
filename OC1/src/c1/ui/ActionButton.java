package c1.ui;

import common.ui.UIButton;
import common.event.StringSource;
import c1.app.CurrentState;
import common.event.Change;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementers must provide onTap.
 * @author Curt
 */
public abstract class ActionButton
    extends UIButton
{

    final String name;
    int textPosition;

    public ActionButton(final String name) {
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
        return "ActionButton:" + name;
    }

    public void setTextPosition(int textPosition) {
        this.textPosition = textPosition;
    }

    public String getText() {
        return text;
    }
}
