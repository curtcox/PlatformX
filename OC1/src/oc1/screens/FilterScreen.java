package oc1.screens;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.screenfactories.FilterScreenFactory;
import oc1.uilist.SearchableList;

/**
 * For filtering provider types.
 *
 * @author Curt
 */
public final class FilterScreen
    extends Screen
{
    private final SearchableList<Type> typeList;

    public FilterScreen(Screen previous,SearchableList<Type> typeList) {
        super("Filter", previous);
        this.typeList = typeList;
        addSelectionListener();
    }

    public static FilterScreen withPrevious(Screen previous) {
        return FilterScreenFactory.withPrevious(previous);
    }
    
    @Override
    public void layoutForPortrait() {
        form.addComponent(typeList.component);
    }
    
    private void addSelectionListener() {
        typeList.onSelected(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Type type = typeList.getSelected();
                SearchScreen.withPreviousAndTypes(FilterScreen.this, new Type[]{type}).show();
            }
        });
    }
}
