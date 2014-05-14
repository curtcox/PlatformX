package com.mycompany.myapp.ui;

import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.event.Change;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementors must provide onTap.
 * @author Curt
 */
public abstract class ActionButton
    extends Button
{
    public ActionButton(String name) {
        super(name);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ActionButton.this.onTap();
            }
        });
    }

    /**
     * Override this method to perform the button tap action.
     */
    public abstract void onTap();
    
    public void updateTextOnChange(Change.Source change,final StringSource source) {
        change.addListener(new Change.Listener(){
            public void onChange() {
                setText(source.getString());
            }
        });
    }

}
