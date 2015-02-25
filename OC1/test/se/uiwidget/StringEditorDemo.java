package se.uiwidget;

import common.event.StringChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StringEditorDemo {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });
    }

    private static StringChange.Listener textListener() {
        return new StringChange.Listener() {
            @Override
            public void onChange(StringChange.Event event) {
                print(event);
            }
        };
    }

    private static KeyListener keyListener() {
        return new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                print(event);
            }
        };
    }

    private static void launch() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new StringEditor(textListener(),keyListener()));
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
    }

    private static void print(Object o) {
        System.out.println(o.toString());
    }
}
