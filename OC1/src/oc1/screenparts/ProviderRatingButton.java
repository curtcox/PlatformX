package oc1.screenparts;

import common.domain.ServiceProvider;
import oc1.screen.ScreenButton;
import oc1.ui.ActionButton;
import oc1.event.StringSource;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public final class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static ActionButton of() {
        return ScreenButton.lazyWithTextAndLeadingTo(buttonText(),"Rate");
    }

    private static StringSource buttonText() {
        return ServiceProvider.getCurrentRatingSource();
    }
}
