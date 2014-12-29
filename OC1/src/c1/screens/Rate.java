package c1.screens;

import com.codename1.ui.Display;
import c1.app.CurrentState;
import common.domain.ServiceProvider;
import c1.screenparts.ScreenButton;
import common.screen.ScreenLink;
import c1.screenparts.ProviderDetailsButton;
import c1.ui.ActionButton;
import common.domain.Rating;
import common.stores.MyRatings;
import common.ui.UILabel;
import common.ui.UITextArea;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
public final class Rate {
    final UILabel rating = new UILabel();
    final UITextArea description = new UITextArea(
        "                                                                    ");
    
    public Rate() {
        description.editable=false;
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
                rating.text=ratingText;
                description.text=ratingDescription;
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
