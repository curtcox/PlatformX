package x.pages;

import x.app.Registry;
import x.domain.ConsumerServiceProvider;
import x.pageparts.ProviderDetailsButton;
import x.pageparts.ProviderRatingButton;
import x.pageparts.XScreenButton;
import x.services.XLocations;
import x.ui.TextPosition;
import x.uiwidget.XButton;

/**
 * The home screen of the application.
 */
public final class Home {
    
    Boolean there_is_a_selected_provider() {
        return ConsumerServiceProvider.getSelected().id != null;
    }

    XButton provider_details() {
        return ProviderDetailsButton.of();
    }

    XButton provider_rating() {
        return ProviderRatingButton.of();
    }

    XButton searchNearbyButton() {
        XButton button = XScreenButton.builder()
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

    XLocations locations() {
        return Registry.get(XLocations.class);
    }

}
