package c1.screenparts;

import common.domain.ServiceProvider;
import common.event.StringSource;
import common.screenparts.ScreenButton;
import common.uiwidget.UIButton;

/**
 * Navigates to the rating screen.
 * @author Curt
 */
public final class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static UIButton of() {
        return ScreenButton.lazyWithTextAndLeadingTo(buttonText(), "Rate");
    }

    private static StringSource buttonText() {
        return ServiceProvider.getCurrentRatingSource();
    }
}
