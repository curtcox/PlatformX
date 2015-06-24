package se.uilist;

import x.uilist.IListCell;
import x.uiwidget.UIImage;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

/**
 * A compound list cell.
 */
public final class SEListCell
    extends JPanel
    implements IListCell
{
    public final JButton firstRow = new JButton();
    public final JButton secondRow = new JButton();
    public final IconButton icon = new IconButton();

    public SEListCell() {
        setLayout(new BorderLayout());
        firstRow.setHorizontalAlignment(SwingConstants.LEFT);
        secondRow.setHorizontalAlignment(SwingConstants.LEFT);
        add(BorderLayout.CENTER, new LabelsPanel(firstRow, secondRow));
        add(BorderLayout.WEST, icon);
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 500;
        int height = 100;
        return new Dimension(width, height);
    }

    @Override
    public Dimension getSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    private static class LabelsPanel extends JPanel {
        LabelsPanel(JButton firstRow, JButton secondRow) {
            this.setLayout(new BorderLayout());
            add(BorderLayout.CENTER, firstRow);
            add(BorderLayout.SOUTH, secondRow);
        }
    }

    private static class IconButton extends JButton {
        @Override
        public Dimension getPreferredSize() {
            int width = 100;
            int height = 100;
            return new Dimension(width, height);
        }

        @Override
        public Dimension getSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }

    @Override
    public void setFirstRowText(String text) {
        firstRow.setText(text);
    }

    @Override
    public void setSecondRowText(String text) {
        secondRow.setText(text);
    }

    @Override
    public void setIcon(UIImage icon) {

    }

    @Override
    public void setIcon(URI uri) {

    }

    public void setLeadComponent(JButton leadComponent) {
    }

}
