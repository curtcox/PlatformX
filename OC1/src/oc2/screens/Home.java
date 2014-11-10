package oc2.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import oc1.domain.ServiceProvider;
import oc1.screen.ScreenButton;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import oc1.services.Locations;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class Home {
    
    Boolean there_is_a_selected_provider() {
        return ServiceProvider.getSelected().id != null;
    }

    Button provider_details() {
        return ProviderDetailsButton.of();
    }

    Button provider_rating() {
        return ProviderRatingButton.of();
    }

    Button searchNearbyButton() {
        Button button = ScreenButton.textImageActionAndLeadingTo("Search nearby", "system-search-4.png", clearLocationSelection(), "Search");
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
