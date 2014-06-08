package com.mycompany.myapp.ui;

import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.log.LogManager;

/**
 * Skeletal implementation of a button that does something when you tap it.
 * Implementors must provide onTap.
 * @author Curt
 */
public abstract class ActionButton
    extends Button
{

    public ActionButton(final String name) {
        super(name);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                log("tapped " + name);
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

    public void updateTextOnChange(final StringSource source) {
        CurrentState.get().addListener(new Change.Listener(){
            public void onChange() {
                setText(source.getString());
            }
        });
    }

    public void setIcon(String icon) {
        setIcon(Icons.of().getImage(icon));
    }
    
    private void log(String message) {
        LogManager.of().getLog(ActionButton.class).log(message);    
    }

}
