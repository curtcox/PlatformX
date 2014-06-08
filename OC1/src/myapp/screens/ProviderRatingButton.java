package myapp.screens;

import myapp.domain.ServiceProvider;
import myapp.ui.ActionButton;
import myapp.ui.StringSource;

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
