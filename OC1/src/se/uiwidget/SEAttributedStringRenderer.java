package se.uiwidget;

import common.ui.AttributedString;
import common.ui.ColumnBoxFlowLayout;
import common.ui.SimpleAttributedStringRenderer;

import java.awt.*;

final class SEAttributedStringRenderer
    implements AttributedString.Renderer
{

    private final Graphics2D g;
    private final int width;

    SEAttributedStringRenderer(int width,Graphics2D g) {
        this.width = width;
        this.g = g;
    }

    public void render(AttributedString text) {
        SimpleAttributedStringRenderer renderer = new SimpleAttributedStringRenderer();
        SEAttributedStringPartRenderer partRenderer = new SEAttributedStringPartRenderer(g);
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(width);
        renderer.drawText(text,partRenderer,layout);
    }

}
