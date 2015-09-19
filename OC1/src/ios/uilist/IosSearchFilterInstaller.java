package ios.uilist;

import ios.uiwidget.IosTextController;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegateAdapter;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class IosSearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void iosSpecificInstall(final IosSearchableList list, final StringToListFilter stringToListFilter) {
        final IosTextController search = list.searchTerm;
        search.setDelegate(new UITextFieldDelegateAdapter() {
            @Override public boolean shouldReturn(UITextField textField) {
                log("shouldReturn",textField);
                endEditing(textField);
                return true;
            }

            @Override public boolean shouldEndEditing(UITextField textField) {
                log("shouldEndEditing",textField);
                applyCurrentFilter(list, stringToListFilter, search.getText());
                return true;
            }

            @Override public boolean shouldChangeCharacters(UITextField textField, NSRange range, String string) {
                log("shouldChangeCharacters",textField);
                applyCurrentFilter(list, stringToListFilter, search.getText());
                return true;
            }

            @Override public void didBeginEditing(UITextField textField) {
                log("didBeginEditing",textField);
                applyCurrentFilter(list, stringToListFilter, search.getText());
            }

            @Override public void didEndEditing(UITextField textField) {
                applyCurrentFilter(list, stringToListFilter, search.getText());
                log("didEndEditing",textField);
            }

            void endEditing(UITextField textField) {
                textField.resignFirstResponder();
            }
        });
        log("Delegate installed on " + search);
    }

    private static void applyCurrentFilter(IosSearchableList list, StringToListFilter stringToListFilter,String text) {
        setFilterText(list.filterListModel,stringToListFilter,text);
        list.reloadData();
    }

    static void setFilterText(IosFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        iosSpecificInstall((IosSearchableList) list, stringToListFilter);
    }

    private static void log(String message,UITextField textField) {
        getLog().log(message + " " + textField);
    }
    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosSearchFilterInstaller.class);
    }
}
