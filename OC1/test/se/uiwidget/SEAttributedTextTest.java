package se.uiwidget;

import common.ui.AttributedStringBuilder;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class SEAttributedTextTest {

    @Test
    public void can_create() {
        assertNotNull(new SEAttributedText(new AttributedStringBuilder().build()));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });
    }

    private static void launch() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SEAttributedText(new AttributedStringBuilder().build()));
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

}