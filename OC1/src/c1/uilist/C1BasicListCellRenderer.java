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
package c1.uilist;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.list.CellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import x.uilist.ListCellConfigurer;

final class C1BasicListCellRenderer<T>
    implements ListCellRenderer<T>, CellRenderer<T>
{
    private final Label focusComponent = new Label();
    private final C1ListCell selected = new C1ListCell();
    private final C1ListCell unselected = new C1ListCell();
    private final Label selectedEntries;
    private final ListCellConfigurer configurer;
    
    public C1BasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
        focusComponent.setUIID(selected.getUIID() + "Focus");
        focusComponent.setFocus(true);
        selectedEntries = selected.secondRow;
    }

    /**
     * @inheritDoc
     */
    public C1ListCell getCellRendererComponent(Component list, Object model, T value, int index, boolean isSelected) {
        if (shouldTreatAsSelected(list,isSelected)) {
            Label entries = selectedEntries;
            selected.setFocus(true);
            entries.setFocus(entries.isFocusable());
            configure(selected,value);
            return selected;
        } else {
            unselected.setFocus(false);
            configure(unselected,value);
            return unselected;
        }
    }

    private void configure(C1ListCell cell, T value) {
        configurer.configureButton(cell, value);
    }
    
    private boolean shouldTreatAsSelected(Component list, boolean isSelected) {
        return isSelected &&
               Display.getInstance().shouldRenderSelection(list) &&
               list.hasFocus();
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

    public C1ListCell getSelected() {
        return selected;
    }

    public C1ListCell getUnselected() {
        return unselected;
    }
}
