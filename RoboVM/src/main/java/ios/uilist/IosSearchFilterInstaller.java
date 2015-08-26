package ios.uilist;

import ios.uiwidget.IosTextController;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegateAdapter;
import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class IosSearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void iosSpecificInstall(final IosSearchableList list, final StringToListFilter stringToListFilter) {
        IosTextController search = list.searchTerm;
        search.setDelegate(new UITextFieldDelegateAdapter() {
            @Override public void didBeginEditing(UITextField textField) {
                applyCurrentFilter(list, stringToListFilter, search.getText());
            }

            @Override public void didEndEditing(UITextField textField) {
                applyCurrentFilter(list, stringToListFilter, search.getText());
            }

        });
    }

    private static void applyCurrentFilter(IosSearchableList list, StringToListFilter stringToListFilter,String text) {
        setFilterText(list.filterListModel,stringToListFilter,text);
    }

    static void setFilterText(IosFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        iosSpecificInstall((IosSearchableList) list, stringToListFilter);
    }
}
