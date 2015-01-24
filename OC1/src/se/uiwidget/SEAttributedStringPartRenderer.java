package se.uiwidget;

import common.ui.AttributedString;

import java.awt.*;

final class SEAttributedStringPartRenderer
    implements AttributedString.PartRenderer
{
    private final Graphics2D g;

    public SEAttributedStringPartRenderer(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void renderPartAt(AttributedString.Part part, Point point) {
        g.setColor(Color.RED);
        g.drawString(part.text,point.x,point.y + 10);
    }

    @Override
    public Dimension size(AttributedString.Part part) {
        FontMetrics fontMetrics = g.getFontMetrics();
        String text = part.text;
        int w = fontMetrics.stringWidth(text);
        int h = fontMetrics.getHeight();
        return new Dimension(w,h);
    }
}
