package se.uiwidget;

import common.ui.AttributedString;
import common.ui.AttributedString.PartRenderer;
import common.ui.ColumnBoxFlowLayout;
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
    private ColumnBoxFlowLayout layout;

    SEAttributedText(AttributedString text) {
        this.text = text;
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        layout = new ColumnBoxFlowLayout(getWidth());
        drawText(new SEAttributedStringRenderer(layout,g2d));
    }

    void drawText(AttributedString.Renderer stringRenderer) {
        stringRenderer.render(text);
    }

    private void drawBackground(Graphics2D g) {
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
        if (layout==null) {
            return null;
        }
        int index = layout.getPointIndex(uiPoint(mouseEvent.getPoint()));
        return index==-1 ? null : new SelectedEvent(text,index);
    }

    @Override public void mousePressed(MouseEvent mouseEvent) {}
    @Override public void mouseReleased(MouseEvent mouseEvent) {}
    @Override public void mouseEntered(MouseEvent mouseEvent) {}
    @Override public void mouseExited(MouseEvent mouseEvent) {}

    protected void onTextSelected(SelectedEvent event) {}

    private common.ui.Point uiPoint(Point point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
