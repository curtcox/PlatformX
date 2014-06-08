package oc1.screens;

import oc1.domain.ServiceProvider;
import oc1.ui.ActionButton;
import oc1.ui.StringSource;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    static ActionButton withReturnTo(final Screen returnScreen) {
        return ScreenButton.lazyWithTextAndLeadingTo(buttonText(),new ScreenFactory() {
            public Screen create() {
                return new RateScreen(returnScreen);
            }
        });
    }

    private static StringSource buttonText() {
        return ServiceProvider.getCurrentRatingSource();
    }
}
