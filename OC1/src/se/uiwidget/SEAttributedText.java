package se.uiwidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class SEAttributedText
    extends JComponent
    implements MouseListener
{
    SEAttributedText() {
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawText(g2d);
    }

    void drawText(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString("text", 0, 0);
        g.drawString("TEXT_DECORATION_NONE", 0, 20);
        g.drawString("TEXT_DECORATION_UNDERLINE", 0, 40);
        g.drawString("TEXT_DECORATION_STRIKETHRU", 0, 60);
        g.drawString("TEXT_DECORATION_OVERLINE", 0, 80);
        g.drawString("TEXT_DECORATION_3D_SHADOW_NORTH", 0, 100);
        g.drawString("TEXT_DECORATION_3D", 0, 120);
        g.drawString("TEXT_DECORATION_3D_LOWERED", 0, 140);
    }

    void drawBackground(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });
    }

    static void launch() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SEAttributedText());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }
}
