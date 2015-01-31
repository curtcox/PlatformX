package se.uiwidget;

import javax.swing.*;
import java.awt.*;

public class StringMapEditorTest {

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
        frame.getContentPane().add(new StringMapEditor());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }
}
