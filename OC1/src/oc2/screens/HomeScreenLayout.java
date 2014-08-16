package oc2.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import oc1.screen.ScreenButton;
import oc1.screen.ScreenContext;
import oc1.screen.ScreenLayout;
import oc1.screenparts.ProviderDetailsButton;
import oc1.screenparts.ProviderRatingButton;
import oc1.services.Locations;
import oc1.ui.GridContainer;

/**
 * The home screen of the application.
 * @author Curt
 */
public final class HomeScreenLayout 
    implements ScreenLayout.Provider
{
    private ScreenContext context;
    
    public ScreenLayout getLayout(ScreenContext context) {
        this.context = context;
        return (isPortrait()) ? layoutForPortrait() : layoutForLandscape();
    }

    private Container newProviderContainer() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(),
            ProviderRatingButton.withReturnTo()
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
        Button button = buttonTo("Search elsewhere","edit-find-9.png","LocationSelection");
        button.setTextPosition(Label.BOTTOM);
        return button;
    }

    private Button searchNearbyScreenButton() {
        Button button = ScreenButton.textImageActionAndLeadingTo(
                "Search nearby","system-search-4.png",
                clearLocationSelection(),
                "Search");
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
        return buttonTo("Filter","filter.png","Filter");
    }

    private Button howToScreenButton() {
        return buttonTo("How To","help.png","HowTo");
    }

    private Button buttonTo(String text, String image, String leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text,image,leadingTo);
    }
    
    protected ScreenLayout layoutForPortrait() {
        if (thereIsASelectedProvider()) {
            return layoutWithSelectedProvider();
        } else {
            return layoutWithNoSelectedProvider();
        }
    }

    protected ScreenLayout layoutForLandscape() {
        if (thereIsASelectedProvider()) {
            return layoutWithSelectedProvider();
        } else {
            return layoutWithNoSelectedProvider();
        }
    }

    private ScreenLayout layoutWithSelectedProvider() {
        if (isPortrait()) {
            return layoutForPortraitWithSelectedProvider();
        } else {
            return layoutForLandscapeWithSelectedProvider();
        }
    }

    private ScreenLayout layoutForLandscapeWithSelectedProvider() {
        return new ScreenLayout(new GridLayout(3,2),
           ProviderDetailsButton.withReturnTo(),
           ProviderRatingButton.withReturnTo(),
           searchElsewhereButton(),
           searchNearbyScreenButton(),
           filterScreenButton(),
           howToScreenButton());
    }

    private ScreenLayout layoutForPortraitWithSelectedProvider() {
        return new ScreenLayout(new GridLayout(2,1),
            newProviderContainer(),
            newNavigationContainer());
    }

    private ScreenLayout layoutWithNoSelectedProvider() {
        if (isPortrait()) {
            return layoutPortraitWithNoSelectedProvider();
        } else {
            return layoutLandscapeWithNoSelectedProvider();
        }
    }

    private ScreenLayout layoutLandscapeWithNoSelectedProvider() {
        return new ScreenLayout(new GridLayout(2,2),
            searchNearbyScreenButton(),
            searchElsewhereButton(),
            filterScreenButton(),
            howToScreenButton());
    }

    private ScreenLayout layoutPortraitWithNoSelectedProvider() {
        return new ScreenLayout(new GridLayout(4,1),
        searchNearbyScreenButton(),
        searchElsewhereButton(),
        filterScreenButton(),
        howToScreenButton());
    }

    private boolean thereIsASelectedProvider() {
        return (Boolean) context.get("there_is_a_selected_provider");
    }

    private boolean isPortrait() {
        return (Boolean) context.get("portrait");
    }

}
