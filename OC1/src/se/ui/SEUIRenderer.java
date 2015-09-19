package se.ui;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

final class SEUIRenderer {

    static JComponent render(XComponent layout) {
        if (layout instanceof XPeeredComponent)  { return peer(layout);   }
        if (layout instanceof XButton)           { return button(layout); }
        if (layout instanceof XLabel)            { return label(layout);  }
        if (layout instanceof XFlow)             { return flow(layout);   }
        if (layout instanceof XColumn)           { return column(layout); }
        if (layout instanceof XRow)              { return row(layout);    }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    private static JComponent peer(XComponent layout) {
        XPeeredComponent peered = (XPeeredComponent) layout;
        return (JComponent) peered.peer;
    }

    static JPanel column(XComponent layout) {
        return box(layout,Y_AXIS);
    }

    static JPanel row(XComponent layout) {
        return box(layout,X_AXIS);
    }

    static JPanel box(XComponent layout,int axis) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,axis));
        for (XComponent component : ((XContainer) layout).components) {
            panel.add(render(component));
        }
        return panel;
    }

    static JPanel flow(XComponent layout) {
        JPanel panel = new JPanel();
        for (XComponent component : ((XContainer) layout).components) {
            panel.add(render(component));
        }
        return panel;
    }

    static JButton button(XComponent layout) {
        final XButton button = (XButton) layout;
        JButton jButton = new JButton(button.text);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button.onTap();
            }
        });
        return jButton;
    }

    static JLabel label(XComponent layout) {
        XLabel label = (XLabel) layout;
        JLabel jlabel = new JLabel(label.text);
        jlabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return jlabel;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(SEUIRenderer.class);
    }

}
