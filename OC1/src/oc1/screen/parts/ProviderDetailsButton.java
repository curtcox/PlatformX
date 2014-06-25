package oc1.screen.parts;

import oc1.domain.ServiceProvider;
import oc1.screens.ProviderDetailsScreen;
import oc1.screens.Screen;
import oc1.screens.ScreenButton;
import oc1.ui.ActionButton;

/**
 * Navigates to a detailed screen for the current provider.
 * @author Curt
 */
public class ProviderDetailsButton {

    public static ActionButton withReturnTo(Screen returnScreen) {
        return ScreenButton.textAndLeadingTo(ServiceProvider.getCurrentName(),new ProviderDetailsScreen(returnScreen));
    }

}
