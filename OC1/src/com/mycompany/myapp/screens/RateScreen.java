package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.domain.CurrentState;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.ui.ActionButton;

/**
 * See
 * http://www.sagetraveling.com/Rating-System-Explanation/
 * @author Curt
 */
final class RateScreen
    extends Screen
{
    private final Label rating = new Label();
    private final Label provider = new Label("Service Provider ???");

    RateScreen(Screen previous) {
        super("Rate",previous);
        addComponents();
        refresh();
    }

    private void addComponents() {
        form.setLayout(new TableLayout(4,1));
        form.addComponent(newProviderSummary());
        form.addComponent(new Label("Rating : Suitable for"));
        form.addComponent(newRatingTable());
        form.addComponent(newChangeLocationButton());
    }

    private Component newProviderSummary() {
        Container container = new Container();
        container.setLayout(new GridLayout(1,2));
        container.addComponent(provider);
        container.addComponent(rating);
        return container;
    }
    
    private Container newRatingTable() {
        Container table = new Container();
        table.setLayout(new TableLayout(5,2));
        addRatingAndInfo(table,"*",    "people who can walk up a flight of stairs");
        addRatingAndInfo(table,"**",   "slow walkers or wheelchair users who can get up a few steps");
        addRatingAndInfo(table,"***",  "wheelchair users with full use of upper body (paraplegics)");
        addRatingAndInfo(table,"****", "wheelchair users with limited arm/hand use");
        addRatingAndInfo(table,"*****","wheelchair users with no arm/hand use (quadriplegics)");
        return table;
    }
    
    private void addRatingAndInfo(Container table, String rating, String description) {
        table.addComponent(newStarButton(rating));
        table.addComponent(new Label(description));
    }
    
    private Button newStarButton(final String text) {
        return new ActionButton(text) {
            public void onTap() {
                rating.setText(text);
                CurrentState.get().selected.myRating = new Rating(text);
            }
        };
    }

    private Component newChangeLocationButton() {
        return new ActionButton("Pick a different location") {
            public void onTap() {
                new SearchScreen(RateScreen.this).show();
            }
        };
    }

    @Override
    protected void refresh() {
        updateProviderCurrentState();
    }
    
    private void updateProviderCurrentState() {
        provider.setText(CurrentState.get().selected.name.toString());
    }

}
