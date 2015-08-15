package x.pageparts;

import x.domain.ServiceProvider;
import x.uiwidget.XButton;

/**
 * Navigates to a detailed screen for the current provider.
 */
public final class ProviderDetailsButton {

    public static XButton of() {
        return XScreenButton.builder()
                .text(ServiceProvider.getCurrentName())
                .leadingTo("ProviderDetails")
                .build();
    }

}
