package x.pageparts;

import x.domain.ConsumerServiceProvider;
import x.uiwidget.XButton;

/**
 * Navigates to a detailed screen for the current provider.
 */
public final class ProviderDetailsButton {

    public static XButton of() {
        return XScreenButton.builder()
                .text(ConsumerServiceProvider.getCurrentName())
                .leadingTo("ProviderDetails")
                .build();
    }

}
