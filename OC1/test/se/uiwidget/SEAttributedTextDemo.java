package se.uiwidget;

import common.ui.AttributedString;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
        frame.getContentPane().add(new SEAttributedText(crawl()));
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

    static AttributedString crawl() {
        return new AttributedString(Arrays.asList(new AttributedString.Part[] {
            part("A long time ago, in a galaxy far, far away...."),
            part("It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire."),
            part("During the battle, rebel spies managed to steal secret plans to the Empire's ultimate weapon, the DEATH STAR, an "),
            part("armored space station with enough power to destroy an entire planet."),
            part("Pursued by the Empire's sinister agents, Princess Leia races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy....")
        }));
    }

    static AttributedString.Part part(String string) {
        return new AttributedString.Part(null,null,null,string);
    }
}