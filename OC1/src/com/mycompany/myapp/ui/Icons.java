package com.mycompany.myapp.ui;

import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Registry;

/**
 *
 * @author Curt
 */
public class Icons {

    private static Resources resources() {
        return Registry.get(Resources.class);
    }

    public static Icons of() {
        return Registry.get(Icons.class);
    }

    public Image getImage(String icon) {
        return resources().getImage(icon);
    }

}