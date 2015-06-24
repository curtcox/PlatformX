package x.pagefactories;

import x.page.PageFactory;

import java.util.List;

public interface ItemListPageFactoryFactory {
    PageFactory newFactory(List values);
}
