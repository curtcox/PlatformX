package com.mycompany.myapp.ui;

import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

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
            public void actionPerformed(ActionEvent evt) {
                ActionButton.this.onTap();
            }
        });
    }

    public abstract void onTap();
}
