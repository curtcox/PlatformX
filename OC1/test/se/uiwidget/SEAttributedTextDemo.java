package se.uiwidget;

import x.ui.AttributedString;
import x.uiwidget.UIAttributedText;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
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
        frame.getContentPane().add(attributedText());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

    private static SEAttributedText attributedText() {
        return new SEAttributedText(crawl()) {
            protected void onTextSelected(UIAttributedText.SelectedEvent event) {
                System.out.println(event.toString());
            }
        };
    }


    static AttributedString crawl() {
        return new AttributedString(Arrays.asList(crawlParts()));
    }

    static AttributedString.Part[] crawlParts() {
        List list = new ArrayList();
        for (String paragraph : crawlParagraphs()) {
            for (String word : paragraph.split(" ")) {
                list.add(part(word + " "));
            }
            list.add(AttributedString.NEW_LINE);
            list.add(part(" "));
            list.add(AttributedString.NEW_LINE);
        }
        return (AttributedString.Part[]) list.toArray(new AttributedString.Part[list.size()]);
    }

    static String[] crawlParagraphs() {
        return new String[] {
            "A long time ago, in a galaxy far, far away....",
            "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.",
            "During the battle, rebel spies managed to steal secret plans to the Empire's ultimate weapon, the DEATH STAR, an armored space station with enough power to destroy an entire planet.",
            "Pursued by the Empire's sinister agents, Princess Leia races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy...."
        };
    }

    static AttributedString.Part part(String string) {
        return new AttributedString.Part(null,null,null,string);
    }
}