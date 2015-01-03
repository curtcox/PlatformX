package c1.screenparts;

import common.domain.ServiceProvider;
import common.screenparts.ScreenButton;
import common.uiwidget.UIButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public final class ProviderDetailsButton {

    public static UIButton of() {
        return ScreenButton.textAndLeadingTo(ServiceProvider.getCurrentName(), "ProviderDetails");
    }

}
