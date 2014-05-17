package com.mycompany.myapp.screens;

import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.ui.GridContainer;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreen
    extends Screen
{
    final Screen rateScreen = new RateScreen(this);
    final Screen searchScreen = new SearchScreen(this);
    final Screen howToScreen = new HowToScreen(this);
    final Screen profileScreen = new ProfileScreen(this);
    
    HomeScreen() {
        super("Oyster Cracker",null);
        layoutForm();
    }

    private void layoutForm() {
        form.setLayout(new GridLayout(2,1));
        form.addComponent(new GridContainer(2,1,
             ProviderDetailsButton.of(this),
             ProviderRatingButton.of(rateScreen)
        ));
        form.addComponent(new GridContainer(2,2,
             ScreenButton.of("Rate",rateScreen),
             ScreenButton.of("Search",searchScreen),
             ScreenButton.of("Profile",profileScreen),
             ScreenButton.of("How To",howToScreen)
        ));
    }
    
    public static void showInitial() {
        HomeScreen home = new HomeScreen();
        home.show();
    }

}
