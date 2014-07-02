package oc1.ui;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.UIManager;
import java.util.Collection;
import java.util.Vector;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
final class DebugList<T> extends List<T> {

    DebugList(ListModel model) {
        super(model);
    }
    
    protected void initLaf(UIManager uim) {
        called("initLaf");
        super.initLaf(uim);
    }

    
    protected void laidOut() {
        called("laidOut");
        super.laidOut();
    }

    protected void modelChanged(int status, int index) {
        called("modelChanged");
        super.modelChanged(index,status);
    }

    protected void listSelectionChanged(int oldSelected, int newSelected) {
        called("listSelectionChanged");
        super.listSelectionChanged(oldSelected, newSelected);
    }

    public int getSideGap() {
        called("getSideGap");
        return super.getSideGap();
    }

    public boolean isScrollableY() {
        called("isScrollableY");
        return super.isScrollableY();
    }

    public boolean isScrollableX() {
        called("isScrollableX");
        return super.isScrollableX();
    }

    public int getMaxElementHeight() {
        called("getMaxElementHeight");
        return super.getMaxElementHeight();
    }

    public void setMaxElementHeight(int maxElementHeight) {
        called("setMaxElementHeight");
        super.setMaxElementHeight(maxElementHeight);
    }

    public int getMinElementHeight() {
        called("getMinElementHeight");
        return super.getMinElementHeight();
    }

    public void setMinElementHeight(int minElementHeight) {
        called("setMinElementHeight");
        super.setMinElementHeight(minElementHeight);
    }

    public int size() {
        called("size");
        return super.size();
    }

    public int getCurrentSelected() {
        called("getCurrentSelected");
        return super.getCurrentSelected();
    }

    public int getSelectedIndex() {
        called("getSelectedIndex");
        return super.getSelectedIndex();
    }

    public void setSelectedIndex(int index) {
        called("setSelectedIndex");
        super.setSelectedIndex(index);
    }

    protected Rectangle getVisibleBounds() {
        called("getVisibleBounds");
        return super.getVisibleBounds();
    }

    protected int getDragRegionStatus(int x, int y) {
        called("getDragRegionStatus");
        return super.getDragRegionStatus(x, y);
    }
    
    public void setSelectedIndex(int index, boolean scrollToSelection) {
        called("setSelectedIndex");
        super.setSelectedIndex(index, scrollToSelection);
    }

    public T getSelectedItem() {
        called("getSelectedItem");
        return super.getSelectedItem();
    }

    public void setSelectedItem(T item) {
        called("setSelectedItem");
        super.setSelectedItem(item);
    }

    public ListModel<T> getModel() {
        called("getModel");
        return super.getModel();
    }

    public void setShouldCalcPreferredSize(boolean shouldCalcPreferredSize) {
        called("setShouldCalcPreferredSize");
        super.setShouldCalcPreferredSize(shouldCalcPreferredSize);
    }

    public void setModel(ListModel model) {
        called("setModel");
        super.setModel(model);
    }

    public boolean isNumericKeyActions() {
        called("isNumericKeyActions");
        return super.isNumericKeyActions();
    }

    public void setNumericKeyActions(boolean numericKeyActions) {
        called("setNumericKeyActions");
        super.setNumericKeyActions(numericKeyActions);
    }

    public boolean isCommandList() {
        called("isCommandList");
        return super.isCommandList();
    }

    public void setCommandList(boolean commandList) {
        called("setCommandList");
        super.setCommandList(commandList);
    }

    public boolean isIgnoreFocusComponentWhenUnfocused() {
        called("isIgnoreFocusComponentWhenUnfocused");
        return super.isIgnoreFocusComponentWhenUnfocused();
    }

    public void setIgnoreFocusComponentWhenUnfocused(boolean ignoreFocusComponentWhenUnfocused) {
        called("setIgnoreFocusComponentWhenUnfocused");
        super.setIgnoreFocusComponentWhenUnfocused(ignoreFocusComponentWhenUnfocused);
    }

    public boolean isMutableRendererBackgrounds() {
        called("isMutableRendererBackgrounds");
        return super.isMutableRendererBackgrounds();
    }

    public void setMutableRendererBackgrounds(boolean mutableRendererBackgrounds) {
        called("setMutableRendererBackgrounds");
        super.setMutableRendererBackgrounds(mutableRendererBackgrounds);
    }

    public int getListSizeCalculationSampleCount() {
        called("getListSizeCalculationSampleCount");
        return super.getListSizeCalculationSampleCount();
    }

    public void setListSizeCalculationSampleCount(int listSizeCalculationSampleCount) {
        called("setListSizeCalculationSampleCount");
        super.setListSizeCalculationSampleCount(listSizeCalculationSampleCount);
    }

    public boolean isLongPointerPressActionEnabled() {
        called("isLongPointerPressActionEnabled");
        return super.isLongPointerPressActionEnabled();
    }

    public void setLongPointerPressActionEnabled(boolean longPointerPressAction) {
        called("setLongPointerPressActionEnabled");
        super.setLongPointerPressActionEnabled(longPointerPressAction);
    }

    public void setRenderer(ListCellRenderer renderer) {
        called("setRenderer");
        super.setRenderer(renderer);
    }

    public void setListCellRenderer(ListCellRenderer renderer) {
        called("setListCellRenderer");
        super.setListCellRenderer(renderer);
    }

    public ListCellRenderer getRenderer() {
        called("getRenderer");
        return super.getRenderer();
    }

    public int getOrientation() {
        called("getOrientation");
        return super.getOrientation();
    }

    public void refreshTheme(boolean merge) {
        called("refreshTheme");
        super.refreshTheme();
    }

    public void setOrientation(int orientation) {
        called("setOrientation");
        super.setOrientation(orientation);
    }

    public void scrollRectToVisible(Rectangle rect) {
        called("scrollRectToVisible");
        super.scrollRectToVisible(rect);
    }

    public void setHandlesInput(boolean b) {
        called("setHandlesInput");
        super.setHandlesInput(b);
    }

    protected void fireClicked() {
        called("fireClicked");
        super.fireClicked();
    }

    protected boolean isSelectableInteraction() {
        called("isSelectableInteraction");
        return super.isSelectableInteraction();
    }

    public void keyReleased(int keyCode) {
        called("keyReleased");
        super.keyReleased(keyCode);
    }

    public void keyPressed(int keyCode) {
        called("keyPressed");
        super.keyPressed(keyCode);
    }

    public void paint(Graphics g) {
        called("paint");
        super.paint(g);
    }

    protected boolean shouldRenderSelection() {
        called("shouldRenderSelection");
        return super.shouldRenderSelection();
    }
    
    public void addSelectionListener(SelectionListener l) {
        called("addSelectionListener");
        super.addSelectionListener(l);
    }

    public void removeSelectionListener(SelectionListener l) {
        called("removeSelectionListener");
        super.removeSelectionListener(l);
    }

    public void addActionListener(ActionListener l) {
        called("addActionListener");
        super.addActionListener(l);
    }

    public Vector getActionListeners() {
        called("getActionListeners");
        return super.getActionListeners();
    }

    public Collection getListeners() {
        called("getListeners");
        return super.getListeners();
    }

    public void removeActionListener(ActionListener l) {
        called("removeActionListener");
        super.removeActionListener(l);
    }

    protected void fireActionEvent() {
        called("fireActionEvent");
        super.fireActionEvent();
    }

    protected void fireActionEvent(ActionEvent a) {
        called("fireActionEvent");
        super.fireActionEvent(a);
    }

    public void setInputOnFocus(boolean inputOnFocus) {
        called("setInputOnFocus");
        super.setInputOnFocus(inputOnFocus);
    }

    public void setPaintFocusBehindList(boolean paintFocusBehindList) {
        called("setPaintFocusBehindList");
        super.setPaintFocusBehindList(paintFocusBehindList);
    }

    public int getItemGap() {
        called("getItemGap");
        return super.getItemGap();
    }

    public void setItemGap(int itemGap) {
        called("setItemGap");
        super.setItemGap(itemGap);
    }

    public void setRenderingPrototype(T renderingPrototype) {
        called("setRenderingPrototype");
        super.setRenderingPrototype(renderingPrototype);
    }

    public T getRenderingPrototype() {
        called("getRenderingPrototype");
        return super.getRenderingPrototype();
    }

    public void longPointerPress(int x, int y) {
        called("longPointerPress");
        super.longPointerPress(x, y);
    }

    public void pointerPressed(int x, int y) {
        called("pointerPressed");
        super.pointerPressed(x, y);
    }

    public void pointerHover(int[] x, int[] y) {
        called("pointerHover");
        super.pointerHover(x, y);
    }

    public void pointerDragged(int x, int y) {
        called("pointerDragged");
        super.pointerDragged(x, y);
    }

    public Rectangle getSelectedRect() {
        called("getSelectedRect");
        return super.getSelectedRect();
    }

    public void setFireOnClick(boolean fireOnClick){
        called("setFireOnClick");
        super.setFireOnClick(fireOnClick);
    }

    public void pointerHoverReleased(int[] x, int[] y) {
        called("pointerHoverReleased");
        super.pointerHoverReleased(x, y);
    }

    public void pointerReleased(int x, int y) {
        called("pointerReleased");
        super.pointerReleased(x, y);
    }

    protected Dimension calcPreferredSize() {
        called("calcPreferredSize");
        return super.calcPreferredSize();
    }

    public void addItem(T item) {
        called("addItem");
        super.addItem(item);
    }

    public int getFixedSelection() {
        called("getFixedSelection");
        return super.getFixedSelection();
    }

    public void setFixedSelection(int fixedSelection) {
        called("setFixedSelection");
        super.setFixedSelection(fixedSelection);
    }

    public boolean animate() {
        called("animate");
        return super.animate();
    }

    protected boolean isTactileTouch(int x, int y) {
        called("isTactileTouch");
        return super.isTactileTouch(x,y);
    }

    public void setScrollToSelected(boolean scrollToSelected) {
        called("setScrollToSelected");
        super.setScrollToSelected(scrollToSelected);
    }

    protected int getGridPosY() {
        called("getGridPosY");
        return super.getGridPosY();
    }

    protected int getGridPosX() {
        called("getGridPosX");
        return super.getGridPosX();
    }

    protected String paramString() {
        called("paramString");
        return super.paramString();
    }

    public void setHint(String hint){
        called("setHint");
        super.setHint(hint);
    }

    public String getHint() {
        called("getHint");
        return super.getHint();
    }

    public void setHintIcon(Image icon){
        called("setHintIcon");
        super.setHintIcon(icon);
    }

    public Image getHintIcon() {
        called("getHintIcon");
        return super.getHintIcon();
    }

    public void setHint(String hint, Image icon){
        called("setHint");
        super.setHint(hint, icon);
    }

    private void called(String method) {
        log(method);
    }
    
    private void log(String message) {
        LogManager.of().getLog(DebugList.class).log(message);    
    }

}
