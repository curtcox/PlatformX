package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.ui.ActionButton;
import com.mycompany.myapp.ui.TableContainer;

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
        form.setLayout(new TableLayout(5,1));
        form.addComponent(newProviderSummary());
        form.addComponent(new Label("Rating : Suitable for"));
        form.addComponent(newRatingTable());
        form.addComponent(newAboutRatingButton());
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
        return new TableContainer(5,2,
            newStarButton("*"),    new Label("people who can walk up a flight of stairs"),
            newStarButton("**"),   new Label("slow walkers or wheelchair users who can get up a few steps"),
            newStarButton("***"),  new Label("wheelchair users with full use of upper body (paraplegics)"),
            newStarButton("****"), new Label("wheelchair users with limited arm/hand use"),
            newStarButton("*****"),new Label("wheelchair users with no arm/hand use (quadriplegics)")
        );
    }
    
    private Button newAboutRatingButton() {
        return new ActionButton("More about this rating scheme") {
            public void onTap() {
                Display.getInstance().execute("http://www.sagetraveling.com/Rating-System-Explanation/");
            }
        };
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
        return ScreenButton.of("Pick a different location",new SearchScreen(RateScreen.this));
    }

    @Override
    protected void refresh() {
        updateProviderCurrentState();
    }
    
    private void updateProviderCurrentState() {
        provider.setText(CurrentState.get().selected.name.toString());
    }

}
