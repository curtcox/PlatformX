package x.pages;

import x.app.Registry;
import x.app.CurrentState;
import x.domain.Rating;
import x.domain.ServiceProvider;
import x.page.PageLink;
import x.pageparts.ProviderDetailsButton;
import x.pageparts.XScreenButton;
import x.stores.MyRatings;
import x.ui.IDisplay;
import x.uiwidget.XButton;
import x.uiwidget.XLabel;
import x.uiwidget.XTextArea;

/**
 * See http://www.sagetraveling.com/Rating-System-Explanation/
 */
public final class Rate {
    final XLabel rating = new XLabel();
    final XTextArea description = new XTextArea(
        "                                                                    ");
    
    public Rate() {
        description.editable=false;
    }

    XButton change_location_button() {
        return XScreenButton.builder().text("Pick a different location").leadingTo(PageLink.of("Search")).build();
    }

    XButton about_rating_button() {
        return new XButton("More about this rating scheme") {
            public void onTap() {
                display().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
    }
    
    XButton rating_button(final String ratingText, final String ratingDescription) {
        XButton button = new XButton("") {
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

    XButton provider_details_button() {
        return ProviderDetailsButton.of();
    }
    
    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }

    IDisplay display() {
        return Registry.get(IDisplay.class);
    }
}
