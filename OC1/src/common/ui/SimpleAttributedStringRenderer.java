package common.ui;

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

    Point drawTextPart(Point point,AttributedString.Part part,AttributedString.PartRenderer renderer, BoxFlowLayout layout) {
        renderer.renderPartAt(part, point);
        Dimension box = renderer.size(part);
        Point upperLeft = layout.addBoxToThisLine(box);
        return new Point(upperLeft.x + box.width,upperLeft.y);
    }

    @Override
    public void drawText(AttributedString text, AttributedString.PartRenderer renderer, BoxFlowLayout layout) {
        Point at = new Point(0,0);
        for (AttributedString.Part part : text) {
            drawTextPart(at,part,renderer,layout);
        }
    }
}
