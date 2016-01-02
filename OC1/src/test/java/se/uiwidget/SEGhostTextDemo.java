package se.uiwidget;

import javax.swing.*;
import java.awt.*;

/**
 * From
 * http://stackoverflow.com/questions/10506789/how-to-display-faint-gray-ghost-text-in-a-jtextfield
 */
public class SEGhostTextDemo {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    public static void init() {
        JFrame frame = new JFrame("Test ghost text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        JButton button = new JButton("Grab focus");
        SEGhostText.installOn(textField, "Please enter some text here...");
        textField.setPreferredSize(new Dimension(300, 24));
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        button.grabFocus();
    }
}
