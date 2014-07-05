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

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.list.CellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import java.util.ArrayList;

/**
 */
public class BasicListCellRenderer<T>
    implements ListCellRenderer<T>, CellRenderer<T>
{
    private ArrayList<Image> pendingAnimations;

    private final Label focusComponent = new Label();
    private final ListCell selected = new ListCell();
    private final ListCell unselected = new ListCell();

    private final Label selectedEntries;
    private final Label unselectedEntries;

    private Component parentList;
    private final boolean firstCharacterRTL;
    private boolean waitingForRegisterAnimation;
    
    public BasicListCellRenderer() {
        if(selected == unselected) {
            throw new IllegalArgumentException("Must use distinct instances for renderer!");
        }
        focusComponent.setUIID(selected.getUIID() + "Focus");
        focusComponent.setFocus(true);

        selectedEntries = (Label) initRenderer(selected)[0];
        unselectedEntries = (Label) initRenderer(unselected)[0];
        firstCharacterRTL = selected.getUIManager().isThemeConstant("firstCharRTLBool", false);
    }

    private Component[] initRenderer(Component r) {
        r.setCellRenderer(true);
        if(r instanceof Container) {
            ArrayList list = new ArrayList();
            findComponentsOfInterest(r, list);
            return vectorToComponentArray(list);
        } else {
            return new Component[] {r};
        }
    }

    private Component[] vectorToComponentArray(ArrayList v) {
        Component[] result = new Component[v.size()];
        for(int iter = 0 ; iter < result.length ; iter++) {
            result[iter] = (Component)v.get(iter);
        }
        return result;
    }

    private void findComponentsOfInterest(Component cmp, ArrayList dest) {
        if(cmp instanceof Container) {
            Container c = (Container)cmp;
            int count = c.getComponentCount();
            for(int iter = 0 ; iter < count ; iter++) {
                findComponentsOfInterest(c.getComponentAt(iter), dest);
            }
            return;
        }
        // performance optimization for fixed images in lists
        if(cmp.getName() != null) {
            if(cmp instanceof Label) {
                Label l = (Label)cmp;
                if(l.getName().toLowerCase().endsWith("fixed") && l.getIcon() != null) {
                    l.getIcon().lock();
                }
                dest.add(cmp);
                return;
            }
            if(cmp instanceof TextArea) {
                dest.add(cmp);
            }
        }
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
            setComponentValue(entries, value, list, selected);
            entries.setFocus(entries.isFocusable());
            return selected;
        } else {
            ListCell cmp = unselected;
            Label entries = unselectedEntries;
            cmp.setFocus(false);
            setComponentValue(entries, value, list, cmp);
            return cmp;
        }
    }

    /**
     * @inheritDoc
     */
    public Component getListCellRendererComponent(List list, T value, int index, boolean isSelected) {
        return getCellRendererComponent(list, list.getModel(), value, index, isSelected);
    }

    private boolean isSelectedValue(Object v) {
        return v != null && "true".equalsIgnoreCase(v.toString());
    }

    public void setComponentValue(Component cmp, Object value, Component parent, Component rootRenderer) {
        if(cmp instanceof Label) {
            setLabelValue((Label)cmp,value,parent,rootRenderer);
        }
        if(cmp instanceof TextArea) {
            setTextAreaValue((TextArea)cmp,value);
        }
    }

    private void setLabelValue(Label label, Object value, Component parent, Component rootRenderer) {
        if (value instanceof Image) {
            setLabelImage(label,(Image)value,parent,rootRenderer);
        } else {
            label.setIcon(null);
        }
        if(label instanceof CheckBox) {
            ((CheckBox)label).setSelected(isSelectedValue(value));
            return;
        }
        if(label instanceof RadioButton) {
            ((RadioButton)label).setSelected(isSelectedValue(value));
            return;
        }
        if (value == null) {
            label.setText("");
        } else {
            if(value instanceof Label){
                label.setText(((Label)value).getText());
                label.setIcon(((Label)value).getIcon());
            }else{
                label.setText(value.toString());
            }
        }
        if(firstCharacterRTL) {
            String t = label.getText();
            if(t.length() > 0) {
                label.setRTL(Display.getInstance().isRTL(t.charAt(0)));
            }
        }
    }

    private void setLabelImage(Label label, Image value, Component parent, Component rootRenderer) {
        if(value.isAnimation()) {
            setLabelAnimation(value,parent,rootRenderer);
        }
        Image oldImage = label.getIcon();
        label.setIcon(value);
        label.setText("");
        if(oldImage == null || oldImage.getWidth() != value.getWidth() || oldImage.getHeight() != value.getHeight()) {
            ((Container)rootRenderer).revalidate();
        }
    }

    private void setLabelAnimation(Image value, Component parent, Component rootRenderer) {
        if(pendingAnimations == null) {
            pendingAnimations = new ArrayList<Image>();
        }
        if(!pendingAnimations.contains(value)) {
            pendingAnimations.add(value);
            if(parentList == null) {
                parentList = parent;
            }
            if(parentList != null) {
                Form f = parentList.getComponentForm();
                waitingForRegisterAnimation = f == null;
            }
        } else {
            if(waitingForRegisterAnimation) {
                if(parentList != null) {
                    Form f = parentList.getComponentForm();
                    if(f != null) {
                        waitingForRegisterAnimation = false;
                    }
                }
            }
        }
    }

    private void setTextAreaValue(TextArea cmp, Object value) {
        if(value == null) {
            cmp.setText("");
        } else {
            cmp.setText(value.toString());
        }
    }

    public Component getListFocusComponent(List list) {
        return focusComponent;
    }

    public Component getFocusComponent(Component list) {
        return focusComponent;
    }

    public Component getSelected() {
        return selected;
    }

    public Component getUnselected() {
        return unselected;
    }
}
