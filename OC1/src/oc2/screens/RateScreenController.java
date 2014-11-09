package oc2.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import hash.NamedExpression;
import oc1.app.CurrentState;
import oc1.domain.ServiceProvider;
import oc1.screen.Getter;
import oc1.screen.ScreenButton;
import oc1.screen.ScreenContext;
import oc1.screen.ScreenController;
import oc1.screen.ScreenLink;
import oc1.screenparts.ProviderDetailsButton;
import oc1.ui.ActionButton;
import oc2.domain.Rating;
import oc2.stores.MyRatings;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
public final class RateScreenController
    extends ScreenController
{
      private final Label rating = new Label();
      private final TextArea description = new TextArea(
        "                                                                    ");
    
    public RateScreenController() {
        description.setEditable(false);
    }

    @Override
    public void addSpecifics(ScreenContext context) {
        context.put("rating", new Getter() { public Component get() {
            return rating;
        }});
        context.put("description", new Getter() { public Component get() {
            return description;
        }});
        context.put("provider_details_button", new Getter() { public Component get() {
            return ProviderDetailsButton.of();
        }});
        context.put("change_location_button", new Getter() { public Component get() {
            return change_location_button();
        }});
        context.put("about_rating_button", new Getter() { public Component get() {
            return about_rating_button();
        }});
        context.put("rating_button", new Getter() { public NamedExpression get() {
            return new NamedExpression("rating_button") {
                public Object invoke(Object[] values) {
                    return rating_button((String)values[0],(String)values[1]);
                }
            };
        }});
    }

    private Component change_location_button() {
        return ScreenButton.textAndLeadingTo("Pick a different location",new ScreenLink("Search"));
    }

    private Button about_rating_button() {
        return new ActionButton("More about this rating scheme") {
            public void onTap() {
                Display.getInstance().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
    }
    
    private Button rating_button(final String ratingText, final String ratingDescription) {
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

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }

}
