package common.ui;

import se.uiwidget.BoxFlowLayout;
import se.uiwidget.SEAttributedText;

import java.awt.*;

public final class SimpleAttributedStringRenderer
    implements SEAttributedText.AttributedStringRenderer
{

//    final AttributedString text;
//    final AttributedString.PartRenderer renderer;
//    final BoxFlowLayout layout;

    public SimpleAttributedStringRenderer() {
//        this.text = text;
//        this.renderer = renderer;
//        this.layout = layout;
    }

//    public void drawText() {
//        for (AttributedString.Part part : text) {
//            drawTextPart(part);
//        }
//    }

//    void drawTextPart(AttributedString.Part part) {
//        Dimension box = renderer.size(part);
//        if (layout.willFitOnThisLine(box)) {
//            Point at = layout.addBoxToThisLine(box);
//            renderer.renderPartAt(part, at);
//            return;
//        }
//    }

    @Override
    public void drawText(AttributedString text, AttributedString.PartRenderer renderer, BoxFlowLayout layout) {
        if (!text.parts.isEmpty()) {
            renderer.renderPartAt(text.parts.get(0), new Point(0, 0));
        }
    }
}
