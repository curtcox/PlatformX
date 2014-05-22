package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
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
   
    HomeScreen() {
        super("Oyster Cracker",null);
        layoutForm();
    }

    private Container newProviderContainer() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(this),
            ProviderRatingButton.withReturnTo(this)
        );
    }

    private Container newNavigationContainer() {
        return new GridContainer(2,2,
            buttonTo("Rate",new RateScreen(this)),
            buttonTo("Search",new SearchScreen(this)),
            buttonTo("Profile",new ProfileScreen(this)),
            buttonTo("How To",new HowToScreen(this))
        );
    }
    
    private Button buttonTo(String text, Screen leadingTo) {
        return ScreenButton.textAndLeadingTo(text,leadingTo);
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
        form.addComponent(newProviderContainer());
        form.addComponent(newNavigationContainer());
    }

    private void layoutWithNoSelectedProvider() {
        form.setLayout(new GridLayout(3,1));
        form.addComponent(buttonTo("Search",new SearchScreen(this)));
        form.addComponent(buttonTo("Profile",new ProfileScreen(this)));
        form.addComponent(buttonTo("How To",new HowToScreen(this)));
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
