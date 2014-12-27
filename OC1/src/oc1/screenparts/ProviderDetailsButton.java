package oc1.screenparts;

import common.domain.ServiceProvider;
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
