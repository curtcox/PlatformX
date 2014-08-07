package oc1.screenparts;

import oc1.domain.ServiceProvider;
import oc1.screen.Screen;
import oc1.screen.ScreenButton;
import oc1.ui.ActionButton;
import oc1.event.StringSource;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static ActionButton withReturnTo(final Screen returnScreen) {
        return ScreenButton.lazyWithTextAndLeadingTo(buttonText(),"RateScreen",returnScreen);
    }

    private static StringSource buttonText() {
        return ServiceProvider.getCurrentRatingSource();
    }
}
