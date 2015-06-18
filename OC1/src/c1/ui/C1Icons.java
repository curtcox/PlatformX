package c1.ui;

import com.codename1.ui.util.Resources;
import common.Registry;
import common.net.Network;
import common.uiwidget.UIImage;

import java.net.URI;

public final class C1Icons {

    private static Resources resources() {
        return Registry.get(Resources.class);
    }

    public static C1Icons of() {
        return Registry.get(C1Icons.class);
    }

    public UIImage getImage(String icon) {
        return translator().translate(resources().getImage(icon));
    }

    public UIImage getImage(URI uri) {
        return Registry.get(Network.class).getImage(uri);
    }

    public UIImage getImage(URI uri, int w, int h) {
        return Registry.get(Network.class).getImage(uri,w,h);
    }

    private C1ImageTranslator translator() {
        return Registry.get(C1ImageTranslator.class);
    }
}
