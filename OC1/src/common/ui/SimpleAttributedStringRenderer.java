package common.ui;

import se.uiwidget.SEAttributedText;

import java.awt.*;

public final class SimpleAttributedStringRenderer
    implements SEAttributedText.AttributedStringRenderer
{

    Point drawTextPart(Point point,AttributedString.Part part,AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        renderer.renderPartAt(part, point);
        Dimension box = renderer.size(part);
        Point upperLeft = layout.addBoxToThisLine(box);
        return new Point(upperLeft.x + box.width,upperLeft.y);
    }

    @Override
    public void drawText(AttributedString text, AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        Point at = new Point(0,0);
        for (AttributedString.Part part : text) {
            drawTextPart(at,part,renderer,layout);
        }
    }
}
