package common.ui;

import se.uiwidget.SEAttributedText;

import java.awt.*;

public final class SimpleAttributedStringRenderer
    implements SEAttributedText.AttributedStringRenderer
{

    @Override
    public void drawText(AttributedString text, AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        for (AttributedString.Part part : text) {
            drawTextPart(part,renderer,layout);
        }
    }

    private void drawTextPart(AttributedString.Part part,AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        if (AttributedString.NEW_LINE.equals(part)) {
            layout.startNextLineWith(new Dimension(0,0));
            return;
        }
        Dimension box = renderer.size(part);
        Point upperLeft = (layout.willFitOnThisLine(box))
                ? layout.addBoxToThisLine(box)
                : layout.startNextLineWith(box);
        renderer.renderPartAt(part, upperLeft);
    }
}
