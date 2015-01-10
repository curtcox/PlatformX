package se.uiwidget;

import common.ui.AttributedString;
import common.ui.AttributedString.*;
import common.ui.AttributedString.Renderer;
import common.uiwidget.UIAttributedText.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class SEAttributedText
    extends JComponent
    implements MouseListener
{
    final AttributedString text;
    private BoxFlowLayout layout;

    SEAttributedText(AttributedString text) {
        this.text = text;
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        layout = new BoxFlowLayout(getSize());
        drawText(new SEAttributedStringRenderer(g2d));
    }

    void drawText(Renderer renderer) {
        for (Part part : text) {
            drawTextPart(part,renderer);
        }
    }


    void drawTextPart(Part part,Renderer renderer) {
        Point at = layout.addRectangle(renderer.size(part));
        Part first = biggestPartThatWillFit(part, renderer);
        renderer.renderPartAt(first, at);
        if (!first.equals(part)) {
            Part rest = part.minusPrefix(first);
            drawTextPart(rest,renderer);
        }
    }

    private Part biggestPartThatWillFit(Part part, Renderer renderer) {
        for (int i=part.size; i>0; i--) {
            Part subPart = part.prefixOfSize(i);
            Dimension textBox = renderer.size(subPart);
            if (layout.willFit(textBox)) {
                return part;
            }
        }
        return part.prefixOfSize(1);
    }

    void drawBackground(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, getWidth(), getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        SelectedEvent event = selectedEvent(mouseEvent);
        if (event!=null) {
            onTextSelected(event);
        }
    }

    private SelectedEvent selectedEvent(MouseEvent mouseEvent) {
        int index = layout.getPointIndex(mouseEvent.getPoint());
        return index==-1 ? null : new SelectedEvent(text,index);
    }

    @Override public void mousePressed(MouseEvent mouseEvent) {}
    @Override public void mouseReleased(MouseEvent mouseEvent) {}
    @Override public void mouseEntered(MouseEvent mouseEvent) {}
    @Override public void mouseExited(MouseEvent mouseEvent) {}

    protected void onTextSelected(SelectedEvent event) {}
}
