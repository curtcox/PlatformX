package se1.app;

import javax.swing.*;
import java.awt.*;

public final class Application {

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
        frame.setVisible(true);
        frame.setSize(400,400);
    }
}
