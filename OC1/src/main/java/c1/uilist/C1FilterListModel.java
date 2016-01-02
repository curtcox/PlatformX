package c1.uilist;

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

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import x.event.Change;
import x.event.LiveList;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import java.util.ArrayList;

/**
 * This class allows filtering/sorting a list model dynamically using a text field
 *
 * @author Shai Almog
 */
final class C1FilterListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> filtered;
    private final XListOffsets<T> offsets;
    private int selectedIndex = -1;
    private final ArrayList<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();
    private final ArrayList<DataChangedListener> changedListeners = new ArrayList<DataChangedListener>();
    
    /**
     * The proxy is applied to the actual model and effectively hides it
     * @param filtered the "real" model for the list
     */
    private C1FilterListModel(LiveList<T> filtered) {
        this.filtered = filtered;
        this.offsets = XListOffsets.of(filtered);
    }

    public static C1FilterListModel of(LiveList underlying) {
        C1FilterListModel model = new C1FilterListModel(underlying);
        model.listenForModelChanges();
        return model;
    }

    private void listenForModelChanges() {
        filtered.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChanged();
            }
        });
    }

    public T getItemAt(int index) {
        return offsets.getElementAt(index);
    }

    public int getSize() {
        return offsets.getSize();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        int old = selectedIndex;
        selectedIndex = index;
        for (SelectionListener selectionListener: selectionListeners) {
            selectionListener.selectionChanged(old,index);
        }
    }

    public void addDataChangedListener(DataChangedListener listener) {
        changedListeners.add(listener);
    }

    public void removeDataChangedListener(DataChangedListener listener) {
        changedListeners.remove(listener);
    }

    public void addSelectionListener(SelectionListener listener) {
        selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
    }

    public void addItem(T item) {
        throw new RuntimeException();
    }

    public void removeItem(int index) {
        throw new RuntimeException();
    }

    void dataChanged(int type, int index) {
        offsets.calculate();
        notifyListenersDataChanged(type, index);
    }

    void dataChanged() {
        dataChanged(DataChangedListener.CHANGED,-1);
    }

    private void notifyListenersDataChanged(int type, int index) {
        for (DataChangedListener listener : changedListeners) {
            listener.dataChanged(type, index);
        }
    }

    public void setFilter(ListFilter filter) {
        offsets.setFilter(filter);
        dataChanged();
    }

}
