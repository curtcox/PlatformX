package com.mycompany.myapp.screens;

import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.ui.GridContainer;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreen
    extends Screen
{
    final Screen rateScreen    = new RateScreen(this);
    final Screen searchScreen  = new SearchScreen(this);
    final Screen howToScreen   = new HowToScreen(this);
    final Screen profileScreen = new ProfileScreen(this);
    final Container providerContainer;
    final Container navigationContainer;
    
    HomeScreen() {
        super("Oyster Cracker",null);
        providerContainer = newProviderContainer();
        navigationContainer = newNavigationContainer();
        layoutForm();
    }

    private Container newProviderContainer() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(this),
            ProviderRatingButton.withReturnTo(rateScreen)
        );
    }

    private Container newNavigationContainer() {
        return new GridContainer(2,2,
            ScreenButton.textAndLeadingTo("Rate",rateScreen),
            ScreenButton.textAndLeadingTo("Search",searchScreen),
            ScreenButton.textAndLeadingTo("Profile",profileScreen),
            ScreenButton.textAndLeadingTo("How To",howToScreen)
        );
    }
    
    private void layoutForm() {
        form.removeAll();
        if (thereIsASelectedProvider()) {
            layoutWithSelectedProvider();
        } else {
            layoutWithNoSelectedProvider();
        }
    }

    private void layoutWithSelectedProvider() {
        form.setLayout(new GridLayout(2,1));
        form.addComponent(providerContainer);
        form.addComponent(navigationContainer);
    }

    private void layoutWithNoSelectedProvider() {
        form.setLayout(new GridLayout(3,1));
        form.addComponent(ScreenButton.textAndLeadingTo("Search",searchScreen));
        form.addComponent(ScreenButton.textAndLeadingTo("Profile",profileScreen));
        form.addComponent(ScreenButton.textAndLeadingTo("How To",howToScreen));
    }

    public static void showInitial() {
        HomeScreen home = new HomeScreen();
        home.show();
    }

    @Override
    protected void refresh() {
        layoutForm();
    }

    private boolean thereIsASelectedProvider() {
        return ServiceProvider.getSelected().id != null;
    }
}
