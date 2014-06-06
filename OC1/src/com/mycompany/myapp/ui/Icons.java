package com.mycompany.myapp.ui;

import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.net.Network;
import java.net.URI;

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

    public Image getImage(URI uri) {
        return Registry.get(Network.class).getImage(uri);
    }

}
