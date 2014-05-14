package com.mycompany.myapp.screens;

import com.codename1.ui.layouts.GridLayout;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreen
    extends Screen
{
    final Screen providerDetailsScreen = new ProviderDetailsScreen(this);
    final Screen rateScreen = new RateScreen(this);
    final Screen searchScreen = new SearchScreen(this);
    final Screen howToScreen = new HowToScreen(this);
    final Screen profileScreen = new ProfileScreen(this);
    
    HomeScreen() {
        super("Oyster Cracker",null);
        layoutForm();
    }

    private void layoutForm() {
        form.setLayout(new GridLayout(3,2));
        form.addComponent(ScreenButton.of("Provider",providerDetailsScreen));
        form.addComponent(ScreenButton.of("****",rateScreen));
        form.addComponent(ScreenButton.of("Rate",rateScreen));
        form.addComponent(ScreenButton.of("Search",searchScreen));
        form.addComponent(ScreenButton.of("Profile",profileScreen));
        form.addComponent(ScreenButton.of("How To",howToScreen));
    }
    
    public static void showInitial() {
        HomeScreen home = new HomeScreen();
        home.show();
    }

}
