package oc1.screens;

import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.ui.BoxYContainer;
import oc1.util.Strings;

/**
 * For filtering provider types.
 *
 * @author Curt
 */
final class FilterScreen
        extends Screen {

    FilterScreen(Screen previous) {
        super("Filter", previous);
    }

    @Override
    protected void layoutForPortrait() {
        form.addComponent(new BoxYContainer(providerButtons()));
    }
    
    private Button[] providerButtons() {
        String[] types = providerTypes();
        Button[] button = new Button[types.length];
        for (int i=0; i<button.length; i++) {
            button[i] = newButton(types[i]);
        }
        return button;
    }

    private Button newButton(final String type) {
        Button icon = new Button(friendly(type));
        icon.setAlignment(Button.LEFT);
        icon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchScreen.withPreviousAndTypes(FilterScreen.this, new Type[]{new Type(type)}).show();
            }
        });
        return icon;
    }
 
    private static String[] providerTypes() {
        return new String[]{
            "accounting", "airport", "amusement_park", "aquarium", "art_gallery", "atm", "bakery", "bank", "bar",
            "beauty_salon", "bicycle_store", "book_store", "bowling_alley", "bus_station", "cafe", "campground",
            "car_dealer", "car_rental", "car_repair", "car_wash", "casino", "cemetery", "church", "city_hall",
            "clothing_store", "convenience_store", "courthouse", "dentist", "department_store", "doctor", "electrician",
            "electronics_store", "embassy", "establishment", "finance", "fire_station", "florist", "food", "funeral_home",
            "furniture_store", "gas_station", "general_contractor", "grocery_or_supermarket", "gym", "hair_care",
            "hardware_store", "health", "hindu_temple", "home_goods_store", "hospital", "insurance_agency", "jewelry_store",
            "laundry", "lawyer", "library", "liquor_store", "local_government_office", "locksmith", "lodging",
            "meal_delivery", "meal_takeaway", "mosque", "movie_rental", "movie_theater", "moving_company", "museum",
            "night_club", "painter", "park", "parking", "pet_store", "pharmacy", "physiotherapist", "place_of_worship",
            "plumber", "police", "post_office", "real_estate_agency", "restaurant", "roofing_contractor", "rv_park",
            "school", "shoe_store", "shopping_mall", "spa", "stadium", "storage", "store", "subway_station", "synagogue",
            "taxi_stand", "train_station", "travel_agency", "university", "veterinary_care", "zoo"
        };
    }

    private static String friendly(String type) {
        return Strings.replace(type,"_"," ");
    }
}
