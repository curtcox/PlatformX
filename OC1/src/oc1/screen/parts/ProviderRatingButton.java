package oc1.screen.parts;

import oc1.domain.ServiceProvider;
import oc1.screens.RateScreen;
import oc1.screens.Screen;
import oc1.screens.ScreenButton;
import oc1.screens.ScreenFactory;
import oc1.ui.ActionButton;
import oc1.ui.StringSource;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static ActionButton withReturnTo(final Screen returnScreen) {
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
