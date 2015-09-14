package an.a22.pagefactories;

import an.a22.uilist.AnSearchFilterInstaller;
import an.a22.uilist.AnSearchableList;
import android.content.Context;
import android.widget.TextView;
import x.Registry;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemToPageLink;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

import java.util.List;

final class AnItemListPageFactory<T>
    implements PageFactory
{
    final PageTags tags;
    final List<T> values;
    final ItemToPageLink itemToPageLink;

    AnItemListPageFactory(PageTags tags, List<T> values, ItemToPageLink itemToPageLink) {
        this.tags = tags;
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return ItemsPage.of(tags,link,newSearchableList(),itemToPageLink);
    }     

    private XSearchableList<T> newSearchableList() {
        AnSearchableList<T> list = AnSearchableList.of(XLiveList.of(values),new TextView(context()),new CellConfigurer());
        AnSearchFilterInstaller.anSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

    private Context context() {
        return Registry.get(Context.class);
    }

}
