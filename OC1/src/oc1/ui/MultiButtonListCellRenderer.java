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

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.list.CellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 */
public class MultiButtonListCellRenderer<T>
    implements ListCellRenderer<T>, CellRenderer<T>
{

    /**
     * The default adapter to use for image URLs
     * @return the defaultAdapter
     */
    public static URLImage.ImageAdapter getDefaultAdapter() {
        return defaultAdapter;
    }

    /**
     * The default adapter to use for image URLs
     * @param aDefaultAdapter the defaultAdapter to set
     */
    public static void setDefaultAdapter(URLImage.ImageAdapter aDefaultAdapter) {
        defaultAdapter = aDefaultAdapter;
    }
    private Button lastClickedComponent;
    private ArrayList<Image> pendingAnimations;

    private final Label focusComponent = new Label();
    private final MultiButton selected;
    private final MultiButton unselected;

    private Component[] selectedEntries;
    private Component[] unselectedEntries;
    private Component[] selectedEntriesEven;
    private Component[] unselectedEntriesEven;

    private Component parentList;
    private boolean selectionListener = true;
    private final boolean firstCharacterRTL;
    private boolean waitingForRegisterAnimation;
    private final HashMap<String, EncodedImage> placeholders = new HashMap<String, EncodedImage>();
    
    private static URLImage.ImageAdapter defaultAdapter = URLImage.RESIZE_SCALE;
    private URLImage.ImageAdapter adapter = defaultAdapter;
    
    /**
     * Constructs a generic renderer with the given selected/unselected components
     *
     * @param selected indicates the selected value for the renderer
     * @param unselected indicates the unselected value for the renderer
     */
    public MultiButtonListCellRenderer(MultiButton selected, MultiButton unselected) {
        if(selected == unselected) {
            throw new IllegalArgumentException("Must use distinct instances for renderer!");
        }
        this.selected = selected;
        this.unselected = unselected;
        focusComponent.setUIID(selected.getUIID() + "Focus");
        focusComponent.setFocus(true);

        selectedEntries = initRenderer(selected);
        unselectedEntries = initRenderer(unselected);
        firstCharacterRTL = selected.getUIManager().isThemeConstant("firstCharRTLBool", false);
        addSelectedEntriesListener(selectedEntries);
        addSelectedEntriesListener(unselectedEntries);
    }

    /**
     * Updates the placeholder instances, this is useful for changing the URLImage placeholder in runtime as
     * might happen in the designer
     */
    public void updateIconPlaceholders() {
        updateIconPlaceholders(selectedEntries);
        updateIconPlaceholders(unselectedEntries);
    }
    
    private void updateIconPlaceholders(Component[] e) {
        for (Component e1 : e) {
            String n = e1.getName();
            if (n != null) {
                if (n.endsWith("_URLImage") && e1 instanceof Label) {
                    placeholders.put(n, (EncodedImage) ((Label) e1).getIcon());
                }
            }
        }
    }
    
    private void addSelectedEntriesListener(Component[] e) {
        for (Component e1 : e) {
            String n = e1.getName();
            if (n != null) {
                if (n.endsWith("_URLImage") && e1 instanceof Label) {
                    placeholders.put(n, (EncodedImage) ((Label) e1).getIcon());
                }
            }
        }
    }

    private Component[] initRenderer(Component r) {
        r.setCellRenderer(true);
        if(r instanceof Container) {
            ArrayList selectedVector = new ArrayList();
            findComponentsOfInterest(r, selectedVector);
            return vectorToComponentArray(selectedVector);
        } else {
            return new Component[] {r};
        }
    }

    /**
     * Allows partitioning the renderer into "areas" that can be clicked. When 
     * receiving an action event in the list this method allows a developer to
     * query the renderer to "see" whether a button within the component was "touched"
     * by the user on a touch screen device. 
     * This method will reset the value to null after returning a none-null value!
     * 
     * @return a button or null
     */
    public Button extractLastClickedComponent() {
        Button c = lastClickedComponent;
        lastClickedComponent = null;
        return c;
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
    public MultiButton getCellRendererComponent(Component list, Object model, T value, int index, boolean isSelected) {
        MultiButton cmp;
        Component[] entries;
        if(!Display.getInstance().shouldRenderSelection(list)) {
            isSelected = false;
        }
        if(isSelected && list.hasFocus()) {
            cmp = selected;
            entries = selectedEntries;
            cmp.setFocus(true);
            setComponentValueWithTickering(entries[0], value, list, cmp);
            entries[0].setFocus(entries[0].isFocusable());
            return cmp;
        } else {
            cmp = unselected;
            entries = unselectedEntries;
            cmp.setFocus(false);
            setComponentValue(entries[0], value, list, cmp);
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

    private void setComponentValueWithTickering(Component cmp, Object value, Component l, Component rootRenderer) {
        setComponentValue(cmp, value, l, rootRenderer);
        if(cmp instanceof Label) {
            if(selectionListener) {
                if(l instanceof List) {
                }
                parentList = l;
            }
            Label label = (Label)cmp;
            if(label.shouldTickerStart() && Display.getInstance().shouldRenderSelection()) {
                if(!label.isTickerRunning()) {
                    parentList = l;
                    if(parentList != null) {
                        Form f = parentList.getComponentForm();
                        if(f != null) {
                            label.startTicker(cmp.getUIManager().getLookAndFeel().getTickerSpeed(), true);
                        }
                    }
                }
            } else {
                if(label.isTickerRunning()) {
                    label.stopTicker();
                }
                label.setTextPosition(0);
            }
        }
    }

    /**
     * Initializes the given component with the given value 
     * 
     * @param cmp one of the components that is or is a part of the renderer
     * @param value the value to install into the component
     */
    private void setComponentValue(Component cmp, Object value, Component parent, Component rootRenderer) {
        // fixed components shouldn't be modified by the renderer, this allows for
        // hardcoded properties in the renderer. We still want them to go through the
        // process so renderer selected/unselected styles are applied
        if(cmp.getName().toLowerCase().endsWith("fixed")) {
            return;
        }
        if(cmp instanceof Label) {
            if(value instanceof Image) {
                Image i = (Image)value;
                if(i.isAnimation()) {
                    if(pendingAnimations == null) {
                        pendingAnimations = new ArrayList<Image>();
                    }
                    if(!pendingAnimations.contains(i)) {
                        pendingAnimations.add(i);
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
                Image oldImage = ((Label)cmp).getIcon();
                ((Label)cmp).setIcon(i);
                ((Label)cmp).setText("");
                if(oldImage == null || oldImage.getWidth() != i.getWidth() || oldImage.getHeight() != i.getHeight()) {
                    ((Container)rootRenderer).revalidate();
                }
                return;
            } else {
                ((Label)cmp).setIcon(null);
            }
            if(cmp instanceof CheckBox) {
                ((CheckBox)cmp).setSelected(isSelectedValue(value));
                return;
            }
            if(cmp instanceof RadioButton) {
                ((RadioButton)cmp).setSelected(isSelectedValue(value));
                return;
            }

            Label l = (Label)cmp;
            if(value == null) {
                l.setText("");
            } else {
                if(value instanceof Label){
                    l.setText(((Label)value).getText());
                    l.setIcon(((Label)value).getIcon());
                }else{
                    l.setText(value.toString());
                }
            }
            if(firstCharacterRTL) {
                String t = l.getText();
                if(t.length() > 0) {
                    l.setRTL(Display.getInstance().isRTL(t.charAt(0)));
                }
            }
            return;
        }
        if(cmp instanceof TextArea) {
            if(value == null) {
                ((TextArea)cmp).setText("");
            } else {
                ((TextArea)cmp).setText(value.toString());
            }
        }
    }

    public Component getListFocusComponent(List list) {
        return focusComponent;
    }

    public Component getFocusComponent(Component list) {
        return focusComponent;
    }

    public boolean isSelectionListener() {
        return selectionListener;
    }

    public void setSelectionListener(boolean selectionListener) {
        this.selectionListener = selectionListener;
    }

    public Component getSelected() {
        return selected;
    }

    public Component getUnselected() {
        return unselected;
    }

    public URLImage.ImageAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(URLImage.ImageAdapter adapter) {
        this.adapter = adapter;
    }

}
