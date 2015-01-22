package se.uiwidget;

import common.ui.AttributedString;
import common.ui.AttributedString.PartRenderer;
import common.ui.BoxFlowLayout;
import common.ui.SimpleAttributedStringRenderer;
import common.uiwidget.UIAttributedText.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SEAttributedText
    extends JComponent
    implements MouseListener
{
    final AttributedString text;
    final AttributedStringRenderer stringRenderer;
    private BoxFlowLayout layout;

    public interface AttributedStringRenderer {
        void drawText(AttributedString text,PartRenderer renderer,BoxFlowLayout layout);
    }

    SEAttributedText(AttributedString text) {
        this(text,new SimpleAttributedStringRenderer());
    }

    SEAttributedText(AttributedString text,AttributedStringRenderer stringRenderer) {
        this.text = text;
        this.stringRenderer = stringRenderer;
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawText(new SEAttributedStringRenderer(g2d));
    }

    void drawText(PartRenderer partRenderer) {
        layout = new BoxFlowLayout(getSize());
        stringRenderer.drawText(text, partRenderer, layout);
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
