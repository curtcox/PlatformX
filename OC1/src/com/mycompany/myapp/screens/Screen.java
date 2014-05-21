package com.mycompany.myapp.screens;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementors will need to override a constructor to layout the UI.
 * @author Curt
 */
public abstract class Screen {

    final Form form;
    final Screen previous;
    final Command back;

    /**
     * Override this constructor to create a new screen.
     */
    Screen(String name, final Screen previous) {
        this(new Form(name),previous);
    }

    /**
     * This constructor is exposed mostly for testing.
     */
    Screen(Form form, final Screen previous) {
        this.form = form;
        this.previous = previous;
        back = previous == null ? null : new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent event) {
                back();
            }
        };
    }

    void show() {
        refresh();
        form.show();
        form.setBackCommand(back);
    }
   
    void back() {
        if (previous!=null) {
            previous.refresh();
            previous.form.showBack();
        }
    }

    /**
     * This is called whenever the screen is shown.
     * Override it in order to update any screen state that might have
     * changed since the last showing.
     */
    protected void refresh() {}
}
