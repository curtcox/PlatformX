package oc2.screens;

import oc1.domain.ServiceProvider;
import oc1.screen.Getter;
import oc1.screen.ScreenContext;
import oc1.screen.ScreenController;

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
    }
}
