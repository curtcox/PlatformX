package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.domain.CurrentState;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.ui.ActionButton;

/**
 *
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
        form.setLayout(new GridLayout(8,1));
        form.addComponent(provider);
        form.addComponent(newChangeLocationButton());
        form.addComponent(new Label("Rating"));
        form.addComponent(rating);
        form.addComponent(newStarButton("*"));
        form.addComponent(newStarButton("**"));
        form.addComponent(newStarButton("***"));
        form.addComponent(newStarButton("****"));
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
