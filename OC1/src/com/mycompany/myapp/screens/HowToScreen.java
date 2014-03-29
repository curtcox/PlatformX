package com.mycompany.myapp.screens;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author Curt
 */
final class HowToScreen
    extends Screen
{

    HowToScreen(Screen previous) {
        super("How To",previous);
        form.setLayout(new GridLayout(2,2));
        form.addComponent(new Label("How to do stuff"));
    }
    
}
