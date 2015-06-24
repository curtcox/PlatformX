package x.pages;

import x.Registry;
import x.app.CurrentState;
import x.domain.Rating;
import x.domain.ServiceProvider;
import x.page.PageLink;
import x.pageparts.ProviderDetailsButton;
import x.pageparts.ScreenButton;
import x.stores.MyRatings;
import x.ui.IDisplay;
import x.uiwidget.UIButton;
import x.uiwidget.UILabel;
import x.uiwidget.UITextArea;

/**
 * See http://www.sagetraveling.com/Rating-System-Explanation/
 */
public final class Rate {
    final UILabel rating = new UILabel();
    final UITextArea description = new UITextArea(
        "                                                                    ");
    
    public Rate() {
        description.editable=false;
    }

    UIButton change_location_button() {
        return ScreenButton.builder().text("Pick a different location").leadingTo(PageLink.of("Search")).build();
    }

    UIButton about_rating_button() {
        return new UIButton("More about this rating scheme") {
            public void onTap() {
                display().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
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

    IDisplay display() {
        return Registry.get(IDisplay.class);
    }
}
