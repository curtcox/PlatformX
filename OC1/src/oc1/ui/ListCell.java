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
package oc1.ui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;

/**
 * A compound list cell.
 */
public class ListCell extends Container {
    public final Label firstRow = new Label("MultiButton");
    public final Label secondRow = new Label();
    public final Label thirdRow = new Label();
    public final Label forthRow = new Label();
    public final Label icon = new Label();
    private Button emblem = new Button();
    private String group;  
    
    /**
     * Default constructor allowing the designer to create an instance of this class
     */
    public ListCell() {
        setLayout(new BorderLayout());
        BorderLayout layout = new BorderLayout();
        Container iconContainer = new Container(layout);
        iconContainer.addComponent(BorderLayout.CENTER, icon);
        Container labels = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container labelsBorder = new Container(new BorderLayout());
        labelsBorder.addComponent(BorderLayout.SOUTH, labels);
        addComponent(BorderLayout.CENTER, labelsBorder);
        addComponent(BorderLayout.WEST, iconContainer);
        layout = new BorderLayout();
        Container emblemContainer = new Container(layout);
        emblemContainer.addComponent(BorderLayout.CENTER, emblem);
        addComponent(BorderLayout.EAST, emblemContainer);
        labelsBorder.addComponent(BorderLayout.CENTER, firstRow);
        labels.addComponent(secondRow);
        labels.addComponent(thirdRow);
        labels.addComponent(forthRow);
        setLeadComponent(emblem);
        Image i = UIManager.getInstance().getThemeImageConstant("defaultEmblemImage");
        if(i != null) {
            emblem.setIcon(i);
        }
        firstRow.setUIID("MultiLine1");
        secondRow.setUIID("MultiLine2");
    }
    
    /**
     * Turns the multi-button into a checkbox multi-button
     * 
     * @param b true for a checkbox multi-button
     */
    public void setCheckBox(boolean b) {
        if(b != isCheckBox()) {
            Container par = emblem.getParent();
            Button old = emblem;
            if(b) {
                emblem = new CheckBox();
            } else {
                emblem = new Button();
            }
            emblem.setUIID(old.getUIID());
            emblem.setName(old.getName());
            java.util.List actionListeners = (java.util.List)old.getListeners();
            if(actionListeners != null) {
                for(int iter = 0 ; iter < actionListeners.size() ; iter++) {
                    emblem.addActionListener((ActionListener)actionListeners.get(iter));
                }
            }
            if(old.getCommand() != null) {
                Image img = old.getIcon();
                emblem.setCommand(old.getCommand());
                emblem.setText("");
                emblem.setIcon(img);
            } else {
                emblem.setText(old.getText());
                if(old.getIcon() != null) {
                    emblem.setIcon(old.getIcon());
                }
            }
            par.replace(old, emblem, null);
            setLeadComponent(emblem);
        }
    }
    
    /**
     * Adds an action listener
     * 
     * @param al the action listener
     */
    public void addActionListener(ActionListener al) {
        emblem.addActionListener(al);
    }

    /**
     * Removes an action listener
     * 
     * @param al the action listener
     */
    public void removeActionListener(ActionListener al) {
        emblem.removeActionListener(al);
    }
    
    /**
     * Returns true if this is a checkbox button
     * 
     * @return true for a checkbox button
     */
    public boolean isCheckBox() {
        return emblem instanceof CheckBox;
    }
    
    /**
     * Turns the multi-button into a radio multi-button
     * 
     * @param b true for a radio multi-button
     */
    public void setRadioButton(boolean b) {
        if(b != isRadioButton()) {
            Container par = emblem.getParent();
            Button old = emblem;
            if(b) {
                emblem = new RadioButton();
                if(group != null) {
                    ((RadioButton)emblem).setGroup(group);
                }
            } else {
                emblem = new Button();
            }
            emblem.setName(old.getName());
            emblem.setUIID(old.getUIID());
            java.util.List actionListeners = (java.util.List)old.getListeners();
            if(actionListeners != null) {
                for(int iter = 0 ; iter < actionListeners.size() ; iter++) {
                    emblem.addActionListener((ActionListener)actionListeners.get(iter));
                }
            }
            if(old.getCommand() != null) {
                Image img = old.getIcon();
                emblem.setCommand(old.getCommand());
                emblem.setText("");
                emblem.setIcon(img);
            }
            par.replace(old, emblem, null);
            setLeadComponent(emblem);
        }
    }
    
    /**
     * Returns true if this is a radio button
     * 
     * @return true for a radio button
     */
    public boolean isRadioButton() {
        return emblem instanceof RadioButton;
    }
    
    /**
     * Returns true if the checkbox/radio button is selected
     * @return true if the checkbox/radio button is selected
     */
    public boolean isSelected() {
        return (emblem instanceof RadioButton || emblem instanceof CheckBox) && emblem.isSelected();
    }
    
    /**
     * Toggles the selected state for the radio button/check box modes
     * @param b true for checked false for unchecked
     */
    public void setSelected(boolean b) {
        if(emblem instanceof RadioButton) {
            ((RadioButton)emblem).setSelected(b);
            return;
        }
        if(emblem instanceof CheckBox) {
            ((CheckBox)emblem).setSelected(b);
        }
    }
    
    /**
     * Indicates the first two labels should be side by side
     * 
     * @param b true to place the first two labels side by side
     */
    public void setHorizontalLayout(boolean b) {
        if(isHorizontalLayout() != b) {
            if(isHorizontalLayout()) {
                secondRow.getParent().getParent().removeComponent(secondRow.getParent());
            }
            secondRow.getParent().removeComponent(secondRow);
            if(b) {
                Container wrapper = new Container();
                Container c = firstRow.getParent();
                wrapper.addComponent(secondRow);
                c.addComponent(BorderLayout.EAST, wrapper);
            } else {
                Container c = thirdRow.getParent();
                c.addComponent(0, secondRow);
            }
        }
    }
    
    /**
     * Indicates whether the first two labels are be side by side
     * 
     * @return true if the first two labels are side by side
     */
    public boolean isHorizontalLayout() {
        return secondRow.getParent().getLayout() instanceof FlowLayout;
    }

}
