package oc2.screens;

import com.codename1.ui.Button;
import oc1.domain.ServiceProvider;
import oc1.screen.Getter;
import oc1.screen.ScreenContext;
import oc1.screen.ScreenController;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import oc1.services.Locations;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreenController
    extends ScreenController
{
    @Override
    public void addSpecifics(ScreenContext context) {
        context.put("there_is_a_selected_provider", new Getter() { public Boolean get() {
            return ServiceProvider.getSelected().id != null;
        }});
        context.put("provider_details", new Getter() { public Button get() {
            return ProviderDetailsButton.of();
        }});
        context.put("provider_rating", new Getter() { public Button get() {
            return ProviderRatingButton.of();
        }});
    }
    
    public void clearLocationSelection() {
        Locations.of().selectLocation(null);
    }

}
