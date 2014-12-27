package oc1.ui;

import common.ui.UIButton;
import oc1.event.StringSource;
import oc1.app.CurrentState;
import oc1.event.Change;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementers must provide onTap.
 * @author Curt
 */
public abstract class ActionButton
    extends UIButton
{

    final String name;
    String text;
    String icon;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
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
