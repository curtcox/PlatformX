package oc1.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import oc1.domain.ServiceProvider;
import oc1.screen.Screen;
import oc1.screen.ScreenButton;
import oc1.screenfactories.SearchScreenFactory;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import oc1.services.Locations;
import oc1.ui.GridContainer;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreen
    extends Screen
{
   
    public static final String VERSION = "Oyster Cracker 2014/8/4 6:31p";
    
    public HomeScreen() {
        super(VERSION,null);
    }

    private Container newProviderContainer() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(this),
            ProviderRatingButton.withReturnTo(this)
        );
    }

    private Container newNavigationContainer() {
        return new GridContainer(2,2,
            searchElsewhereButton(),
            searchNearbyScreenButton(),
            filterScreenButton(),
            howToScreenButton()
        );
    }

    private Button searchElsewhereButton() {
        Button button = buttonTo("Search elsewhere","edit-find-9.png",LocationSelectionScreen.withPrevious(this));
        button.setTextPosition(Label.BOTTOM);
        return button;
    }

    private Button searchNearbyScreenButton() {
        Button button = ScreenButton.textImageActionAndLeadingTo(
                "Search nearby","system-search-4.png",
                clearLocationSelection(),
                SearchScreenFactory.withPrevious(this));
        button.setTextPosition(Label.BOTTOM);
        return button;
    }
    
    private Runnable clearLocationSelection() {
        return new Runnable(){
            public void run() {
                Locations.of().selectLocation(null);
            }
        };
    }
    
    private Button filterScreenButton() {
        return buttonTo("Filter","filter.png",FilterScreen.withPrevious(this));
    }

//    private Button profileScreenButton() {
//        return buttonTo("Profile","configure-4.png",new ProfileScreen(this));
//    }

    private Button howToScreenButton() {
        return buttonTo("How To","help.png",HowToScreen.linkBackTo(this));
    }

    private Button buttonTo(String text, String image, Screen leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text,image,leadingTo);
    }
    
    @Override
    protected void layoutForPortrait() {
        if (thereIsASelectedProvider()) {
            layoutWithSelectedProvider();
        } else {
            layoutWithNoSelectedProvider();
        }
    }

    @Override
    protected void layoutForLandscape() {
        if (thereIsASelectedProvider()) {
            layoutWithSelectedProvider();
        } else {
            layoutWithNoSelectedProvider();
        }
    }

    private void layoutWithSelectedProvider() {
        if (isPortrait()) {
            layoutForPortraitWithSelectedProvider();
        } else {
            layoutForLandscapeWithSelectedProvider();
        }
    }

    private void layoutForLandscapeWithSelectedProvider() {
        form.setLayout(new GridLayout(3,2));
        add(ProviderDetailsButton.withReturnTo(this));
        add(ProviderRatingButton.withReturnTo(this));
        add(searchElsewhereButton());
        add(searchNearbyScreenButton());
        add(filterScreenButton());
        add(howToScreenButton());
    }

    private void layoutForPortraitWithSelectedProvider() {
        form.setLayout(new GridLayout(2,1));
        add(newProviderContainer());
        add(newNavigationContainer());
    }

    private void layoutWithNoSelectedProvider() {
        if (isPortrait()) {
            layoutPortraitWithNoSelectedProvider();
        } else {
            layoutLandscapeWithNoSelectedProvider();
        }
    }

    private void layoutLandscapeWithNoSelectedProvider() {
        form.setLayout(new GridLayout(2,2));
        add(searchNearbyScreenButton());
        add(searchElsewhereButton());
        add(filterScreenButton());
        add(howToScreenButton());
    }

    private void layoutPortraitWithNoSelectedProvider() {
        form.setLayout(new GridLayout(4,1));
        add(searchNearbyScreenButton());
        add(searchElsewhereButton());
        add(filterScreenButton());
        add(howToScreenButton());
    }

    private void add(Component component) {
        form.addComponent(component);
    }
    
    @Override
    protected void refresh() {
        layoutForm();
    }

    private boolean thereIsASelectedProvider() {
        return ServiceProvider.getSelected().id != null;
    }

}
