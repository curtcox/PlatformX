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
import common.uilist.ListFilter;

import java.util.ArrayList;

/**
 * This class allows filtering/sorting a list model dynamically using a text field
 *
 * @author Shai Almog
 */
public final class C1FilterListModel<T>
    implements ListModel<T>
{
    private final ListModel<T> underlying;
    private ListFilter filter;
    private ArrayList<Integer> offsets = new ArrayList();
    private final ArrayList<DataChangedListener> listeners = new ArrayList<DataChangedListener>();
    
    /**
     * The proxy is applied to the actual model and effectively hides it
     * @param underlying the "real" model for the list
     */
    private C1FilterListModel(ListModel<T> underlying) {
        this.underlying = underlying;
        this.filter = ListFilter.ALLOW_ALL;
    }

    public static C1FilterListModel of(ListModel underlying) {
        C1FilterListModel model = new C1FilterListModel(underlying);
        model.calculateOffsets();
        model.listenForModelChanges();
        return model;
    }

    private void listenForModelChanges() {
        underlying.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                C1FilterListModel.this.dataChanged(type,index);
            }
        });
    }

    public T getItemAt(int index) {
        return underlying.getItemAt(offsets.get(index));
    }

    public int getSize() {
        return offsets.size();
    }

    public int getSelectedIndex() {
        return Math.max(0, getUnderlyingOffset(underlying.getSelectedIndex()));
    }

    private int getUnderlyingOffset(int index) {
        return offsets.indexOf(index);
    }

    public void setSelectedIndex(int index) {
        if(index < 0) {
            underlying.setSelectedIndex(index);
        } else {
            underlying.setSelectedIndex(offsets.get(index));
        }
    }

    public void addDataChangedListener(DataChangedListener listener) {
        listeners.add(listener);
    }

    public void removeDataChangedListener(DataChangedListener listener) {
        listeners.remove(listener);
    }

    public void addSelectionListener(SelectionListener listener) {
        underlying.addSelectionListener(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        underlying.removeSelectionListener(listener);
    }

    public void addItem(T item) {
        throw new RuntimeException();
    }

    public void removeItem(int index) {
        throw new RuntimeException();
    }

    void dataChanged(int type, int index) {
        calculateOffsets();
        notifyListenersDataChanged(type,index);
    }

    private void notifyListenersDataChanged(int type, int index) {
        for (DataChangedListener listener : listeners) {
            listener.dataChanged(type, index);
        }
    }

    private void calculateOffsets() {
        offsets = new ArrayList();
        for (int i=0; i<underlying.getSize(); i++) {
            T item = underlying.getItemAt(i);
            if (filter.passes(item)) {
                offsets.add(i);
            }
        }
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
        dataChanged(DataChangedListener.CHANGED,-1);
    }

}
