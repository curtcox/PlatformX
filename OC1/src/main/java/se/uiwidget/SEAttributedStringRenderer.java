package se.uiwidget;

import x.ui.AttributedString;
import x.ui.ColumnBoxFlowLayout;
import x.ui.SimpleAttributedStringRenderer;

import java.awt.*;

final class SEAttributedStringRenderer
    implements AttributedString.Renderer
{

    private final ColumnBoxFlowLayout layout;
    private final Graphics2D g;

    SEAttributedStringRenderer(ColumnBoxFlowLayout layout,Graphics2D g) {
        this.layout = layout;
        this.g = g;
    }

    public void render(AttributedString text) {
        SimpleAttributedStringRenderer renderer = new SimpleAttributedStringRenderer();
        SEAttributedStringPartRenderer partRenderer = new SEAttributedStringPartRenderer(g);
        g.setColor(Color.BLACK);
        renderer.drawText(text, partRenderer, layout);
    }

}
