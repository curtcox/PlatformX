/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
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
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores
 * CA 94065 USA or visit www.oracle.com if you need additional information or
 * have any questions.
 */
package oc1.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.list.CellRenderer;
import com.codename1.ui.list.ListCellRenderer;

/**
 */
public class BasicListCellRenderer<T>
    implements ListCellRenderer<T>, CellRenderer<T>
{
    private final Label focusComponent = new Label();
    private final ListCell selected = new ListCell();
    private final ListCell unselected = new ListCell();

    private final Label selectedEntries;

    public BasicListCellRenderer() {
        focusComponent.setUIID(selected.getUIID() + "Focus");
        focusComponent.setFocus(true);
        selectedEntries = selected.secondRow;
    }

    /**
     * @inheritDoc
     */
    public ListCell getCellRendererComponent(Component list, Object model, T value, int index, boolean isSelected) {
        if(!Display.getInstance().shouldRenderSelection(list)) {
            isSelected = false;
        }
        if(isSelected && list.hasFocus()) {
            Label entries = selectedEntries;
            selected.setFocus(true);
            entries.setFocus(entries.isFocusable());
            return selected;
        } else {
            ListCell cmp = unselected;
            cmp.setFocus(false);
            return cmp;
        }
    }

    /**
     * @inheritDoc
     */
    public Component getListCellRendererComponent(List list, T value, int index, boolean isSelected) {
        return getCellRendererComponent(list, list.getModel(), value, index, isSelected);
    }

    public Component getListFocusComponent(List list) {
        return focusComponent;
    }

    public Component getFocusComponent(Component list) {
        return focusComponent;
    }

    public ListCell getSelected() {
        return selected;
    }

    public ListCell getUnselected() {
        return unselected;
    }
}
