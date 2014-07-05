package oc1.screens;

import oc1.screen.ScreenButton;
import oc1.screen.Screen;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import oc1.domain.ServiceProvider;
import oc1.ui.GridContainer;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreen
    extends Screen
{
   
    public static final String VERSION = "Oyster Cracker 2014/7/5 7:32a";
    
    HomeScreen() {
        super(VERSION,null);
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
            ratingScreenButton(),
            searchScreenButton(),
            profileScreenButton(),
            howToScreenButton()
        );
    }

    private Button ratingScreenButton() {
        return buttonTo("Rate","rating.png",new RateScreen(this));
    }

    private Button searchScreenButton() {
        return buttonTo("Search","system-search-4.png",SearchScreen.of(this));
    }
    
    private Button profileScreenButton() {
        return buttonTo("Profile","configure-4.png",new ProfileScreen(this));
    }

    private Button howToScreenButton() {
        return buttonTo("How To","help.png",new HowToScreen(this));
    }

    private Button buttonTo(String text, String image, Screen leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text,image,leadingTo);
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
        form.addComponent(searchScreenButton());
        form.addComponent(profileScreenButton());
        form.addComponent(howToScreenButton());
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
