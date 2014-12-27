package oc1.ui;

import common.Registry;
import common.ui.UIButton;
import oc1.screen.ScreenFactory;
import oc1.screen.ScreenLink;

/**
 * A button that follows a link when you tap it.
 * @author Curt
 */
public final class LinkButton
    extends UIButton
{
    public LinkButton(final String name, final ScreenLink.Factory linkFactory) {
//        super(name);
//        addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                screenFactory().create(linkFactory.create()).show();
//            }
//        });
    }
        
    public static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }

}
