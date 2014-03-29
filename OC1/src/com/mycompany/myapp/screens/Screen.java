package com.mycompany.myapp.screens;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

/**
 *
 * @author Curt
 */
abstract class Screen {

    final Form form;
    final Screen previous;
    final Command back;

    Screen(String name, final Screen previous) {
        form = new Form(name);
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
