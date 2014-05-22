package com.mycompany.myapp.screens;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.ui.ActionButton;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    static ActionButton withReturnTo(final Screen returnScreen) {
        return ScreenButton.lazyWithTextAndLeadingTo(ServiceProvider.getCurrentRating(),new ScreenFactory() {
            public Screen create() {
                return new RateScreen(returnScreen);
            }
        });
    }

}
