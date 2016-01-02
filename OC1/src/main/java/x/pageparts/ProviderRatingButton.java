package x.pageparts;

import x.domain.ConsumerServiceProvider;
import x.event.StringSource;
import x.uiwidget.XButton;

/**
 * Navigates to the rating screen.
 */
public final class ProviderRatingButton {

    private ProviderRatingButton() {}
    
    public static XButton of() {
        return XScreenButton.builder()
                .text(buttonText())
                .leadingTo("Rate")
                .build();
    }

    private static StringSource buttonText() {
        return ConsumerServiceProvider.getCurrentRatingSource();
    }
}
