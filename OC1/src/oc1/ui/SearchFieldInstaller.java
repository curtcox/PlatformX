package oc1.ui;

import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.list.FilterProxyListModel;

/**
 *
 * @author Curt
 */
final class SearchFieldInstaller {
    
    static void install(final TextField search, final IList list) {
        if (list instanceof List) {
            FilterProxyListModel.install(search, (List) list);
        }
    }
}
