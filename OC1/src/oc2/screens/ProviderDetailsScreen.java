package oc2.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import java.net.URI;
import java.util.Arrays;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.screen.*;
import oc1.screenparts.ProviderRatingButton;
import oc1.ui.Icons;
import oc1.ui.LinkButton;

/**
 * For showing details about a particular provider.
 * @author Curt
 */
public final class ProviderDetailsScreen
    extends Screen
{
    private final Label name = new Label();
    private final Label distance = new Label();
    private final Label types = new Label();
    private final Label price = new Label();
    private final Label rating = new Label();
    private final Button icon = new LinkButton("",new SearchLinkFactory());
    
    private final class SearchLinkFactory implements ScreenLink.Factory {
        public ScreenLink create() {
            return new ScreenLink("Search",getType());
        }
    }

    public static ScreenFactory FACTORY = new GlobScreenFactory("ProviderDetails") {
        public Screen doCreate(ScreenLink link) {
            return new ProviderDetailsScreen();
        }     
    };

    private final Label vicinity = new Label();

    ProviderDetailsScreen() {
        super("Provider Details");
    }
    
    @Override
    public ScreenLayout layoutForPortrait() {
        return new ScreenLayout(new TableLayout(8,1),
                                    name,
                                    distance,
                                    vicinity,
                                    price,
                                    rating,
                                    icon,
                                    types,
                                    ProviderRatingButton.of());
    }
    
    @Override
    protected void refresh() {
        updateName();
        updateRating();
        updatePrice();
        updateIcon();
        updateTypes();
        updateDistance();
        updateVicinity();
        super.refresh();
    }

    private void updateName() {
        name.setText(provider().name.toString());
    }

    private void updateRating() {
        this.rating.setText(getRatingText());
    }

    private void updatePrice() {
        price.setText(getPriceText());
    }

    private Type[] getType() {
        return new Type[] {provider().types[0]};
    }
    
    private String getRatingText() {
        return provider().rating == null ? "No rating information" : "Rating : " + provider().rating;
    }

    private String getPriceText() {
        return provider().priceLevel == null ? "No price information" : "Price Level : " + provider().priceLevel;
    }

    private void updateIcon() {
        URI uri = provider().icon;
        if (uri!=null) {
            icon.setIcon(Icons.of().getImage(uri));
        }
    }
    
    private void updateTypes() {
        types.setText(Arrays.asList(provider().types).toString());
    }

    private void updateDistance() {
        distance.setText(provider().distanceFromCurrentLocation());
    }

    private void updateVicinity() {
        vicinity.setText(provider().address.toString());
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
}
