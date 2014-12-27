package c1.screenparts;

import common.domain.ServiceProvider;
import c1.ui.ActionButton;
import common.event.StringSource;

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
