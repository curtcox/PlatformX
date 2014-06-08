package oc1.screens;

import oc1.domain.ServiceProvider;
import oc1.ui.ActionButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public class ProviderDetailsButton {

    static ActionButton withReturnTo(Screen returnScreen) {
        return ScreenButton.textAndLeadingTo(ServiceProvider.getCurrentName(),new ProviderDetailsScreen(returnScreen));
    }

}
