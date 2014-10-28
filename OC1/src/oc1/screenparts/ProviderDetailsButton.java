package oc1.screenparts;

import oc1.domain.ServiceProvider;
import oc1.screen.ScreenButton;
import oc1.ui.ActionButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public final class ProviderDetailsButton {

    public static ActionButton of() {
        return ScreenButton.textAndLeadingTo(ServiceProvider.getCurrentName(),"ProviderDetails");
    }

}
