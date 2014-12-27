package oc2.screens;

import com.codename1.ui.Label;
import common.domain.ServiceProvider;
import oc1.screen.ScreenButton;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import oc1.services.Locations;
import oc1.ui.ActionButton;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class Home {
    
    Boolean there_is_a_selected_provider() {
        return ServiceProvider.getSelected().id != null;
    }

    ActionButton provider_details() {
        return ProviderDetailsButton.of();
    }

    ActionButton provider_rating() {
        return ProviderRatingButton.of();
    }

    ActionButton searchNearbyButton() {
        ActionButton button = ScreenButton.textImageActionAndLeadingTo("Search nearby", "system-search-4.png", clearLocationSelection(), "Search");
        button.setTextPosition(Label.BOTTOM);
        return button;
    }

    Runnable clearLocationSelection() {
        return new Runnable() {
            public void run() {
                Locations.of().selectLocation(null);
            }
        };
    }

}
