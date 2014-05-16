package com.mycompany.myapp.screens;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.ui.ActionButton;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public class ProviderRatingButton {

    static ActionButton of(Screen returnScreen) {
        return ScreenButton.of(ServiceProvider.getCurrentRating(),new RateScreen(returnScreen));
    }

}
