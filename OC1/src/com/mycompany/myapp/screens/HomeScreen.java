package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.ui.ActionButton;

/**
 *
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
        form.setLayout(new GridLayout(2,2));
        form.addComponent(newRateButton());
        form.addComponent(newSearchButton());
        form.addComponent(newProfileButton());
        form.addComponent(newHowToButton());
    }
    
    public static void showInitial() {
        HomeScreen home = new HomeScreen();
        home.show();
    }

    private Button newRateButton() {
        return new ActionButton("Rate") {
            public void onTap() {
                rateScreen.show();
            }
        };
    }

    private Button newSearchButton() {
        return new ActionButton("Search") {
            public void onTap() {
                searchScreen.show();
            }
        };
    }

    private Button newHowToButton() {
        return new ActionButton("How To") {
            public void onTap() {
                howToScreen.show();
            }
        };
    }

    private Button newProfileButton() {
        return new ActionButton("Profile") {
            public void onTap() {
                profileScreen.show();
            }
        };
    }
    
}
