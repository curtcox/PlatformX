package oc2.screens;

import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import oc1.app.CurrentState;
import common.domain.ServiceProvider;
import oc1.screen.ScreenButton;
import oc1.screen.ScreenLink;
import oc1.screenparts.ProviderDetailsButton;
import oc1.ui.ActionButton;
import common.domain.Rating;
import oc2.stores.MyRatings;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
public final class Rate {
    final Label rating = new Label();
    final TextArea description = new TextArea(
        "                                                                    ");
    
    public Rate() {
        description.setEditable(false);
    }

    ActionButton change_location_button() {
        return ScreenButton.textAndLeadingTo("Pick a different location",new ScreenLink("Search"));
    }

    ActionButton about_rating_button() {
        return new ActionButton("More about this rating scheme") {
            public void onTap() {
                Display.getInstance().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
    }
    
    ActionButton rating_button(final String ratingText, final String ratingDescription) {
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

    void rateCurrentProvider(Rating rating) {
        provider().rate(rating);
        MyRatings.of().put(provider().id,rating);
        CurrentState.get().broadcastChange();
    }

    ActionButton provider_details_button() {
        return ProviderDetailsButton.of();
    }
    
    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }

}
