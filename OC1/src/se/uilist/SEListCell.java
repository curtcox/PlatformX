package se.uilist;

import common.uilist.IListCell;
import common.uiwidget.UIImage;

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
    public final JButton icon = new JButton();

    /**
     * Default constructor allowing the designer to create an instance of this class
     */
    public SEListCell() {
        setLayout(new BorderLayout());
        firstRow.setHorizontalAlignment(SwingConstants.LEFT);
        secondRow.setHorizontalAlignment(SwingConstants.LEFT);
        add(BorderLayout.CENTER, createLabelsBorder(firstRow, secondRow));
        add(BorderLayout.WEST, createIconContainer(icon));
    }

    private static JComponent createIconContainer(JButton icon) {
        BorderLayout layout = new BorderLayout();
        JComponent container = new JPanel(layout);
        container.add(BorderLayout.CENTER, icon);
        return container;
    }

    private static JComponent createLabelsBorder(JButton firstRow, JButton secondRow) {
        JComponent labels = new JPanel();
        labels.setLayout(new BoxLayout(labels,BoxLayout.Y_AXIS));
        JComponent labelsBorder = new JPanel(new BorderLayout());
        labelsBorder.add(BorderLayout.SOUTH, labels);
        labelsBorder.add(BorderLayout.CENTER, firstRow);
        labels.add(secondRow);
        return labelsBorder;
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
