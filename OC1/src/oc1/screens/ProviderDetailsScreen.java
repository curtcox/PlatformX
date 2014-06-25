package oc1.screens;

import oc1.screen.Screen;
import oc1.screenparts.ProviderRatingButton;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import oc1.domain.ServiceProvider;
import oc1.ui.Icons;
import java.util.Arrays;

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
    private final Label icon = new Label();
    private final Label vicinity = new Label();

    public ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
        form.setLayout(new TableLayout(8,1));
        form.addComponent(name);
        form.addComponent(distance);
        form.addComponent(vicinity);
        form.addComponent(price);
        form.addComponent(rating);
        form.addComponent(icon);
        form.addComponent(types);
        form.addComponent(ProviderRatingButton.withReturnTo(this));
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
    }

    private void updateName() {
        name.setText(provider().name.toString());
    }

    private void updateRating() {
        Double rating = provider().rating;
        this.rating.setText((rating == null) ? "No rating information" : "Rating : " + rating);
    }

    private void updatePrice() {
        Double priceLevel = provider().priceLevel;
        price.setText((priceLevel == null) ? "No price information" : "Price Level : " + priceLevel);
    }

    private void updateIcon() {
        icon.setIcon(Icons.of().getImage(provider().icon));
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
