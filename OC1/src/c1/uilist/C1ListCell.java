/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package c1.uilist;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import x.uilist.IListCell;
import x.uiwidget.UIImage;

import java.net.URI;

/**
 * A compound list cell.
 */
public final class C1ListCell
    extends Container
    implements IListCell
{
    public final Button firstRow = new Button("MultiButton");
    public final Button secondRow = new Button();
    public final Button icon = new Button();
    
    /**
     * Default constructor allowing the designer to create an instance of this class
     */
    public C1ListCell() {
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, createLabelsBorder(firstRow,secondRow));
        addComponent(BorderLayout.WEST, createIconContainer(icon));
    }

    private static Container createIconContainer(Label icon) {
        BorderLayout layout = new BorderLayout();
        Container container = new Container(layout);
        container.addComponent(BorderLayout.CENTER, icon);
        return container;
    }

    private static Container createLabelsBorder(Label firstRow, Label secondRow) {
        Container labels = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container labelsBorder = new Container(new BorderLayout());
        labelsBorder.addComponent(BorderLayout.SOUTH, labels);
        labelsBorder.addComponent(BorderLayout.CENTER, firstRow);
        labels.addComponent(secondRow);
        firstRow.setUIID("MultiLine1");
        secondRow.setUIID("MultiLine2");
        return labelsBorder;
    }

    @Override
    public void setFirstRowText(String text) {
        firstRow.setText(text);
    }

    @Override
    public void setSecondRowText(String text) {
        secondRow.setText(text);
    }

    @Override
    public void setIcon(UIImage icon) {

    }

    @Override
    public void setIcon(URI uri) {

    }
}
