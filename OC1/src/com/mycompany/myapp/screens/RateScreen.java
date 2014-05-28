package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.stores.MyRatings;
import com.mycompany.myapp.ui.ActionButton;
import com.mycompany.myapp.ui.GridContainer;
import com.mycompany.myapp.ui.TableContainer;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
final class RateScreen
    extends Screen
{
    private final Label rating = new Label();
    private final TextArea description = new TextArea(
        "                                                                    ");
    
    RateScreen(Screen previous) {
        super("Rate",previous);
        description.setEditable(false);
        addComponents();
        refresh();
    }

    private void addComponents() {
        form.setLayout(new TableLayout(6,1));
        form.addComponent(newProviderSummary());
        form.addComponent(new Label("Rating : Suitable for"));
        form.addComponent(newRatingTable());
        form.addComponent(description);
        form.addComponent(newAboutRatingButton());
        form.addComponent(newChangeLocationButton());
    }

    private Component newProviderSummary() {
        return new GridContainer(2,1,
            ProviderDetailsButton.withReturnTo(this),
            rating
        );
    }
    
    private Container newRatingTable() {
        return new TableContainer(1,4,
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
        return new ActionButton(" * ") {
            public void onTap() {
                rating.setText(ratingText);
                description.setText(ratingDescription);
                rateCurrentProvider(new Rating(ratingText));
            }
        };
    }

    private void rateCurrentProvider(Rating rating) {
        provider().rate(rating);
        MyRatings.of().put(provider().id,rating);
        CurrentState.get().broadcastChange();
    }
    
    private Component newChangeLocationButton() {
        return ScreenButton.textAndLeadingTo("Pick a different location",new SearchScreen(RateScreen.this));
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
    
    @Override
    protected void refresh() {
        rating.setText(provider().myRating().toString());
    }

}
