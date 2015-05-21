package common.screenparts;

import common.domain.ServiceProvider;
import common.event.StringSource;
import common.uiwidget.UIButton;

/**
 * Navigates to the rating screen.
 */
public final class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static UIButton of() {
        return ScreenButton.builder()
                .text(buttonText())
                .leadingTo("Rate")
                .build();
    }

    private static StringSource buttonText() {
        return ServiceProvider.getCurrentRatingSource();
    }
}
