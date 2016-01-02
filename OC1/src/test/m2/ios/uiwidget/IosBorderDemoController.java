package ios.uiwidget;

import org.robovm.apple.uikit.*;

public class IosBorderDemoController
        extends UIViewController
{
    private final UIButton center;
    private final UIButton north;
    private final UIButton east;
    private final UIButton west;

    public IosBorderDemoController() {
        center = newButton("center",UIColor.blue());
        north = newButton("north", UIColor.white());
        east = newButton("east",UIColor.brown());
        west = newButton("west",UIColor.orange());
        layoutView();
    }

    private void layoutView() {
        setView(IosBorderContainer.of(center)
                .north(north)
                .east(east)
                .west(west));
    }

    private static UIButton newButton(String title, UIColor color) {
        UIButton button = UIButton.create(UIButtonType.RoundedRect);
        button.setTitle(title, UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));
        button.setBackgroundColor(color);
        return button;
    }

}
