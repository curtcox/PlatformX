package common.ui;

import se.uiwidget.SEAttributedText;

import java.awt.*;

public final class SimpleAttributedStringRenderer
    implements SEAttributedText.AttributedStringRenderer
{

    void drawTextPart(AttributedString.Part part,AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        Dimension box = renderer.size(part);
        Point upperLeft = (layout.willFitOnThisLine(box))
            ? layout.addBoxToThisLine(box)
            : layout.startNextLineWith(box);
        renderer.renderPartAt(part, upperLeft);
    }

    @Override
    public void drawText(AttributedString text, AttributedString.PartRenderer renderer, ColumnBoxFlowLayout layout) {
        for (AttributedString.Part part : text) {
            drawTextPart(part,renderer,layout);
        }
    }
}
