package common.pages;

import common.domain.ServiceProvider;
import common.domain.Type;
import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.page.dynamic.GlobPageFactory;
import common.pageparts.ProviderRatingButton;
import common.ui.LinkButton;
import common.uiwidget.UIButton;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import common.uiwidget.UITable;

import java.net.URI;
import java.util.Arrays;

/**
 * For showing details about a particular provider.
 */
public final class ProviderDetailsPage
    extends Page
{
    private final UILabel name = new UILabel();
    private final UILabel distance = new UILabel();
    private final UILabel types = new UILabel();
    private final UILabel price = new UILabel();
    private final UILabel rating = new UILabel();
    private final UILabel vicinity = new UILabel();
    private final UIButton icon = new LinkButton("",new SearchLinkFactory());
    
    private final class SearchLinkFactory implements PageLink.Factory {
        public PageLink create() {
            return PageLink.of("Search", getType());
        }
    }

    public static PageFactory FACTORY = GlobPageFactory.filter("ProviderDetails", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{new ProviderDetailsPage(link)};
        }
    });

    ProviderDetailsPage(PageLink link) {
        super(link);
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new UITable(8,1,
                    name,
                    distance,
                    vicinity,
                    price,
                    rating,
                    icon,
                    types,
                    ProviderRatingButton.of()
        );
    }
    
    @Override
    public void refresh() {
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
        name.text = provider().name.toString();
    }

    private void updateRating() {
        this.rating.text = getRatingText();
    }

    private void updatePrice() {
        price.text= getPriceText();
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
            icon.icon = uri.toString();
        }
    }
    
    private void updateTypes() {
        types.text = Arrays.asList(provider().types).toString();
    }

    private void updateDistance() {
        distance.text = provider().distanceFromCurrentLocation();
    }

    private void updateVicinity() {
        vicinity.text=provider().address.toString();
    }

    private ServiceProvider provider() {
        return ServiceProvider.getSelected();
    }
}
