package common.screenparts;

import common.domain.ServiceProvider;
import common.uiwidget.UIButton;

/**
 * Navigates to a detailed screen for the current provider.
 */
public final class ProviderDetailsButton {

    public static UIButton of() {
        return ScreenButton.builder()
                .text(ServiceProvider.getCurrentName())
                .leadingTo("ProviderDetails")
                .build();
    }

}
