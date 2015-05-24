package c1.screens;

import common.Registry;
import common.domain.ServiceProvider;
import common.screenparts.ScreenButton;
import common.screenparts.ProviderDetailsButton;
import common.screenparts.ProviderRatingButton;
import common.services.LocationService;
import common.ui.TextPosition;
import common.uiwidget.UIButton;

/**
 * The home screen of the application.
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
        UIButton button = ScreenButton.builder()
                .text("Search nearby")
                .image("system-search-4.png")
                .action(clearLocationSelection())
                .leadingTo("Search")
                .build();
        button.setTextPosition(TextPosition.BOTTOM);
        return button;
    }

    Runnable clearLocationSelection() {
        return new Runnable() {
            public void run() {
                locations().selectLocation(null);
            }
        };
    }

    LocationService locations() {
        return Registry.get(LocationService.class);
    }

}
