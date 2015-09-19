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
    final JButton firstRow = new JButton();
    final JButton secondRow = new JButton();
    final IconButton icon = new IconButton();

    SEListCell() {
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
            int height = 100;
            int width = getIcon() == null ? 0 : height;
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
        if (config.second!=null && config.second.length() > 0) {
            secondRow.setVisible(true);
            secondRow.setText(config.second);
        } else {
            secondRow.setVisible(false);
        }
    }

}
