package com.mycompany.myapp.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.table.TableLayout;

/**
 *
 * @author curt
 */
public final class TableContainer
    extends Container
{
    public TableContainer(int rows, int columns, Component... components) {
        setLayout(new TableLayout(rows,columns));
        for (Component component : components) {
            addComponent(component);
        }
    }
}
