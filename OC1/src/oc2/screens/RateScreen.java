package oc2.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.table.TableLayout;
import oc1.app.CurrentState;
import oc2.domain.Rating;
import oc1.domain.ServiceProvider;
import oc1.screen.GlobScreenFactory;
import oc1.screen.Screen;
import oc1.screen.ScreenButton;
import oc1.screen.ScreenFactory;
import oc1.screen.ScreenLink;
import oc1.screenparts.ProviderDetailsButton;
import oc2.stores.MyRatings;
import oc1.ui.ActionButton;
import oc1.ui.GridContainer;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
public final class RateScreen
    extends Screen
{
    private final Label rating = new Label();
    private final TextArea description = new TextArea(
        "                                                                    ");
    
    private RateScreen() {
        super("Rate");
    }

    public static ScreenFactory FACTORY = new GlobScreenFactory("Rate") {
        public Screen doCreate(ScreenLink link) {
            return of();
        }     
    };

    static RateScreen of() {
        RateScreen screen = new RateScreen();
        screen.init();
        return screen;
    }

    private void init() {
        description.setEditable(false);
    }
    
    @Override
    public void layoutForPortrait() {
        form.setLayout(new TableLayout(6,1));
        add(newProviderSummary());
        add(new Label("Rating : Suitable for"));
        add(newRatingTable());
        add(description);
        add(newAboutRatingButton());
        add(newChangeLocationButton());
    }

    private Component newProviderSummary() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(),
            rating
        );
    }
    
    private void add(Component component) {
        form.addComponent(component);
    }

    private Container newRatingTable() {
        return new GridContainer(1,5,
            newRatingButton("*",    "people who can walk up a flight of stairs"),
            newRatingButton("**",   "slow walkers or wheelchair users who can get up a few steps"),
            newRatingButton("***",  "wheelchair users with full use of upper body (paraplegics)"),
            newRatingButton("****", "wheelchair users with limited arm/hand use"),
            newRatingButton("*****","wheelchair users with no arm/hand use (quadriplegics)")
        );
    }
    
    private Button newAboutRatingButton() {
        return new ActionButton("More about this rating scheme") {
            public void onTap() {
                Display.getInstance().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
    }
    
    private Button newRatingButton(final String ratingText, final String ratingDescription) {
        ActionButton button = new ActionButton("") {
            public void onTap() {
                rating.setText(ratingText);
                description.setText(ratingDescription);
                rateCurrentProvider(new Rating(ratingText));
            }
        };
        button.setIcon("rating.png");
        return button;
    }

    private void rateCurrentProvider(Rating rating) {
        provider().rate(rating);
        MyRatings.of().put(provider().id,rating);
        CurrentState.get().broadcastChange();
    }
    
    private Component newChangeLocationButton() {
        return ScreenButton.textAndLeadingTo("Pick a different location",new ScreenLink("Search",this));
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
    
    @Override
    protected void refresh() {
        rating.setText(provider().myRating().toString());
    }

}
