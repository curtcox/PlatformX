package com.mycompany.myapp.screens;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author Curt
 */
final class ProviderDetailsScreen
    extends Screen
{

    ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
        form.setLayout(new GridLayout(2,2));
        form.addComponent(new Label("Provider Details"));
    }
    
}
