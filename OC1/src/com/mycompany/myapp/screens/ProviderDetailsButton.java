package com.mycompany.myapp.screens;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.ui.ActionButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public class ProviderDetailsButton {

    static ActionButton withReturnTo(Screen returnScreen) {
        return ScreenButton.textAndLeadingTo(ServiceProvider.getCurrentName(),new ProviderDetailsScreen(returnScreen));
    }

}
