package an.a22.uilist;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import x.app.Registry;
import x.uilist.XSearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;
import x.util.Runner;

public final class AnSearchFilterInstaller
    implements XSearchFilterInstaller
{
    
    public static void anSpecificInstall(final AnSearchableList list, final StringToListFilter stringToListFilter) {
        final TextView search = list.searchTerm;
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                runner().invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        applyCurrentFilter(list, stringToListFilter, search.getText().toString());
                    }
                });
            }
            @Override public void afterTextChanged(Editable editable) {}
        });
    }

    private static void applyCurrentFilter(AnSearchableList list, StringToListFilter stringToListFilter,String text) {
        setFilterText(list.filterListModel,stringToListFilter,text);
    }

    static void setFilterText(AnFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        anSpecificInstall((AnSearchableList) list, stringToListFilter);
    }

    private static Runner runner() {
        return Registry.get(Runner.class);
    }
}
