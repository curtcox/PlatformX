package oc1.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;
import java.util.Arrays;
import oc1.domain.ServiceProvider;
import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.screenparts.ProviderRatingButton;
import oc1.ui.Icons;

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
    private final Button icon = new Button();
    private final Label vicinity = new Label();

    private ProviderDetailsScreen(Screen previous) {
        super("Provider Details",previous);
    }
    
    public static ProviderDetailsScreen linkBackTo(Screen previous) {
        ProviderDetailsScreen screen = new ProviderDetailsScreen(previous);
        screen.addButtonListener();
        return screen;
    }
    
    private void addButtonListener() {
        icon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchScreen.withPreviousAndTypes(ProviderDetailsScreen.this, getType()).show();
            }
        });
    }
    
    @Override
    public void layoutForm() {
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
