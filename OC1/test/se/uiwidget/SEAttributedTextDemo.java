package se.uiwidget;

import common.ui.AttributedString;

import javax.swing.*;
import java.awt.*;

public class SEAttributedTextDemo {

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
        frame.getContentPane().add(new SEAttributedText(new AttributedString(crawl())));
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

    private static String crawl() {
        return
        "A long time ago, in a galaxy far, far away...." +
        "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire." +
        "During the battle, rebel spies managed to steal secret plans to the Empire's ultimate weapon, the DEATH STAR, an " +
        "armored space station with enough power to destroy an entire planet." +
        "Pursued by the Empire's sinister agents, Princess Leia races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy....";
    }

}