package se.uiwidget;

import common.ui.AttributedString;

import java.awt.*;

final class SEAttributedStringRenderer
    implements AttributedString.Renderer
{

    private final Graphics2D g;

    SEAttributedStringRenderer(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void renderPartAt(AttributedString.Part part, Point point) {
        g.setColor(Color.BLACK);
        g.drawString("text", 0, 0);
        g.drawString("TEXT_DECORATION_NONE", 0, 20);
        g.drawString("TEXT_DECORATION_UNDERLINE", 0, 40);
        g.drawString("TEXT_DECORATION_STRIKETHRU", 0, 60);
        g.drawString("TEXT_DECORATION_OVERLINE", 0, 80);
        g.drawString("TEXT_DECORATION_3D_SHADOW_NORTH", 0, 100);
        g.drawString("TEXT_DECORATION_3D", 0, 120);
        g.drawString("TEXT_DECORATION_3D_LOWERED", 0, 140);
    }

    @Override
    public Dimension size(AttributedString.Part part) {
        return new Dimension();
    }
}
