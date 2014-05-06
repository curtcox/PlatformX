package com.mycompany.myapp.screens;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * @author Curt
 */
public abstract class Screen {

    final Form form;
    final Screen previous;
    final Command back;

    Screen(String name, final Screen previous) {
        this(new Form(name),previous);
    }

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

    protected void refresh() {}
}
