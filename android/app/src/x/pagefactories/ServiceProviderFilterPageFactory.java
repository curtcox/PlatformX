package x.pagefactories;

import x.Registry;
import x.domain.Type;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.dynamic.GlobPageFactory;
import x.pageparts.TypeListCellConfigurer;
import x.pageparts.TypeTextFilter;
import x.pages.FilterPage;
import x.uilist.ISearchFilterInstaller;
import x.uiwidget.XLabel;
import x.uiwidget.XSearchableList;

import java.util.ArrayList;
import java.util.List;

public final class ServiceProviderFilterPageFactory {

    public static PageFactory FACTORY = GlobPageFactory.filter("Filter", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
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

    private static XSearchableList<Type> newSearchableList() {
        XSearchableList list = listFactory().from(getTypes(),new XLabel(),new TypeListCellConfigurer());
        installer().install(list, new TypeTextFilter());
        return list;
    }

    private static XSearchableList.Factory listFactory() {
        return Registry.get(XSearchableList.Factory.class);
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
