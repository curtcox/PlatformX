package x.pages;

import x.domain.ConsumerServiceProvider;
import x.domain.Type;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.dynamic.GlobPageFactory;
import x.pageparts.ProviderRatingButton;
import x.ui.LinkButton;
import x.uiwidget.XButton;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;
import x.uiwidget.XTable;

import java.net.URI;
import java.util.Arrays;

/**
 * For showing details about a particular provider.
 */
public final class ProviderDetailsPage
    extends Page
{
    private final XLabel name = new XLabel();
    private final XLabel distance = new XLabel();
    private final XLabel types = new XLabel();
    private final XLabel price = new XLabel();
    private final XLabel rating = new XLabel();
    private final XLabel vicinity = new XLabel();
    private final XButton icon = new LinkButton("",new SearchLinkFactory());
    
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
    public XComponent layoutForPortrait() {
        return new XTable(8,1,
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

    private ConsumerServiceProvider provider() {
        return ConsumerServiceProvider.getSelected();
    }
}
