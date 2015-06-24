package common.pagefactories;

import common.page.PageFactory;

import java.util.List;

public interface ItemListPageFactoryFactory {
    PageFactory newFactory(List values);
}
