package c1.screens;

import com.codename1.ui.Display;
import common.app.CurrentState;
import common.domain.ServiceProvider;
import common.screenparts.ScreenButton;
import common.screen.ScreenLink;
import c1.screenparts.ProviderDetailsButton;
import common.domain.Rating;
import common.stores.MyRatings;
import common.uiwidget.UIButton;
import common.uiwidget.UILabel;
import common.uiwidget.UITextArea;

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

    UIButton change_location_button() {
        return ScreenButton.textAndLeadingTo("Pick a different location",ScreenLink.of("Search"));
    }

    UIButton about_rating_button() {
        return new UIButton("More about this rating scheme") {
            public void onTap() {
                Display.getInstance().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
    }
    
    UIButton rating_button(final String ratingText, final String ratingDescription) {
        UIButton button = new UIButton("") {
            public void onTap() {
                rating.text=ratingText;
                description.text=ratingDescription;
                rateCurrentProvider(new Rating(ratingText));
            }
        };
        button.icon="rating.png";
        return button;
    }

    void rateCurrentProvider(Rating rating) {
        provider().rate(rating);
        MyRatings.of().put(provider().id,rating);
        CurrentState.get().broadcastChange();
    }

    UIButton provider_details_button() {
        return ProviderDetailsButton.of();
    }
    
    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }

}
