package se.uilist;

import x.uilist.IXListCell;

import javax.swing.*;
import java.awt.*;

/**
 * A compound list cell.
 */
final class SEListCell
    extends JPanel
    implements IXListCell
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
    public void apply(Config config) {
        firstRow.setText(config.first);
        secondRow.setText(config.second);
    }

}
