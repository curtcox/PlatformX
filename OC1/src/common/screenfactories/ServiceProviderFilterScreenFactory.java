package common.screenfactories;

import common.Registry;
import common.domain.Type;
import common.screen.Page;
import common.screen.PageFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobPageFactory;
import common.screenparts.TypeListCellConfigurer;
import common.screenparts.TypeTextFilter;
import common.screens.FilterPage;
import common.uilist.ISearchFilterInstaller;
import common.uiwidget.ISearchableList;
import common.uiwidget.UILabel;

import java.util.ArrayList;
import java.util.List;

public final class ServiceProviderFilterScreenFactory {

    public static PageFactory FACTORY = GlobPageFactory.filter("Filter", new PageFactory() {
        @Override
        public Page[] create(ScreenLink link) {
            return new Page[]{new FilterPage(link, newSearchableList())};
        }
    });

    private static List<Type> getTypes() {
        List<Type> list = new ArrayList<Type>();
        for (String name : providerTypeNames()) {
            list.add(new Type(name));
        }
        return list;
    }

    private static ISearchableList<Type> newSearchableList() {
        ISearchableList list = listFactory().from(getTypes(),new UILabel(),new TypeListCellConfigurer());
        installer().install(list, new TypeTextFilter());
        return list;
    }

    private static ISearchableList.Factory listFactory() {
        return Registry.get(ISearchableList.Factory.class);
    }

    private static ISearchFilterInstaller installer() {
        return Registry.get(ISearchFilterInstaller.class);
    }

    private static String[] providerTypeNames() {
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

}
