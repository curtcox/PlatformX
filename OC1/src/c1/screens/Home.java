package c1.screens;

import com.codename1.ui.Label;
import common.domain.ServiceProvider;
import common.screenparts.ScreenButton;
import c1.screenparts.ProviderDetailsButton;
import c1.screenparts.ProviderRatingButton;
import c1.services.Locations;
import common.uiwidget.UIButton;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class Home {
    
    Boolean there_is_a_selected_provider() {
        return ServiceProvider.getSelected().id != null;
    }

    UIButton provider_details() {
        return ProviderDetailsButton.of();
    }

    UIButton provider_rating() {
        return ProviderRatingButton.of();
    }

    UIButton searchNearbyButton() {
        UIButton button = ScreenButton.textImageActionAndLeadingTo("Search nearby", "system-search-4.png", clearLocationSelection(), "Search");
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
