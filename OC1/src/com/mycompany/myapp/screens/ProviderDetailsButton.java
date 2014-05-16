package com.mycompany.myapp.screens;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.ui.ActionButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public class ProviderDetailsButton {

    static ActionButton of(Screen returnScreen) {
        return ScreenButton.of(ServiceProvider.getCurrentName(),new ProviderDetailsScreen(returnScreen));
    }

}
