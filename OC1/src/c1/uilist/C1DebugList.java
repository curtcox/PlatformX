package c1.uilist;

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

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

final class C1DebugList<T> extends List<T> {

    C1DebugList(ListModel model) {
        super(model);
    }
    
    @Override
    protected void initLaf(UIManager uim) {
        called("initLaf");
        super.initLaf(uim);
    }
    
    @Override
    protected void laidOut() {
        called("laidOut");
        super.laidOut();
    }

    @Override
    protected void modelChanged(int status, int index) {
        called("modelChanged");
        super.modelChanged(index,status);
    }

    @Override
    protected void listSelectionChanged(int oldSelected, int newSelected) {
        called("listSelectionChanged");
        super.listSelectionChanged(oldSelected, newSelected);
    }

    @Override
    public int getSideGap() {
        int result = super.getSideGap();
        debug("getSideGap=" + result);
        return result;
    }

    @Override
    public boolean isScrollableY() {
        boolean result = super.isScrollableY();
        debug("isScrollableY="+result);
        return result;
    }

    @Override
    public boolean isScrollableX() {
        boolean result = super.isScrollableX();
        debug("isScrollableX="+result);
        return result;
    }

    @Override
    public int getMaxElementHeight() {
        int result = super.getMaxElementHeight();
        debug("getMaxElementHeight=" + result);
        return result;
    }

    @Override
    public void setMaxElementHeight(int maxElementHeight) {
        called("setMaxElementHeight");
        super.setMaxElementHeight(maxElementHeight);
    }

    @Override
    public int getMinElementHeight() {
        int result = super.getMinElementHeight();
        debug("getMinElementHeight=" + result);
        return result;
    }

    @Override
    public void setMinElementHeight(int minElementHeight) {
        called("setMinElementHeight");
        super.setMinElementHeight(minElementHeight);
    }

    @Override
    public int size() {
        int result = super.size();
        debug("size="+result);
        return result;
    }

    @Override
    public int getCurrentSelected() {
        int result = super.getCurrentSelected();
        debug("getCurrentSelected=" + result);
        return result;
    }

    @Override
    public int getSelectedIndex() {
        int result = super.getSelectedIndex();
        debug("getSelectedIndex=" + result);
        return result;
    }

    @Override
    public void setSelectedIndex(int index) {
        called("setSelectedIndex");
        super.setSelectedIndex(index);
    }

    @Override
    protected Rectangle getVisibleBounds() {
        called("getVisibleBounds");
        return super.getVisibleBounds();
    }

    @Override
    protected int getDragRegionStatus(int x, int y) {
        called("getDragRegionStatus(" + x + "," + y + ")");
        return super.getDragRegionStatus(x, y);
    }
    
    @Override
    public void setSelectedIndex(int index, boolean scrollToSelection) {
        called("setSelectedIndex");
        super.setSelectedIndex(index, scrollToSelection);
    }

    @Override
    public T getSelectedItem() {
        T result = super.getSelectedItem();
        called("getSelectedItem=" + result);
        return result;
    }

    @Override
    public void setSelectedItem(T item) {
        called("setSelectedItem");
        super.setSelectedItem(item);
    }

    @Override
    public ListModel<T> getModel() {
        debug("getModel");
        return super.getModel();
    }

    @Override
    public void setShouldCalcPreferredSize(boolean shouldCalcPreferredSize) {
        called("setShouldCalcPreferredSize");
        super.setShouldCalcPreferredSize(shouldCalcPreferredSize);
    }

    @Override
    public void setModel(ListModel model) {
        called("setModel");
        super.setModel(model);
    }

    @Override
    public boolean isNumericKeyActions() {
        called("isNumericKeyActions");
        return super.isNumericKeyActions();
    }

    @Override
    public void setNumericKeyActions(boolean numericKeyActions) {
        called("setNumericKeyActions");
        super.setNumericKeyActions(numericKeyActions);
    }

    @Override
    public boolean isCommandList() {
        called("isCommandList");
        return super.isCommandList();
    }

    @Override
    public void setCommandList(boolean commandList) {
        called("setCommandList");
        super.setCommandList(commandList);
    }

    @Override
    public boolean isIgnoreFocusComponentWhenUnfocused() {
        called("isIgnoreFocusComponentWhenUnfocused");
        return super.isIgnoreFocusComponentWhenUnfocused();
    }

    @Override
    public void setIgnoreFocusComponentWhenUnfocused(boolean ignoreFocusComponentWhenUnfocused) {
        called("setIgnoreFocusComponentWhenUnfocused");
        super.setIgnoreFocusComponentWhenUnfocused(ignoreFocusComponentWhenUnfocused);
    }

    @Override
    public boolean isMutableRendererBackgrounds() {
        called("isMutableRendererBackgrounds");
        return super.isMutableRendererBackgrounds();
    }

    @Override
    public void setMutableRendererBackgrounds(boolean mutableRendererBackgrounds) {
        called("setMutableRendererBackgrounds");
        super.setMutableRendererBackgrounds(mutableRendererBackgrounds);
    }

    @Override
    public int getListSizeCalculationSampleCount() {
        int result = super.getListSizeCalculationSampleCount();
        called("getListSizeCalculationSampleCount=" + result);
        return result;
    }

    @Override
    public void setListSizeCalculationSampleCount(int listSizeCalculationSampleCount) {
        called("setListSizeCalculationSampleCount");
        super.setListSizeCalculationSampleCount(listSizeCalculationSampleCount);
    }

    @Override
    public boolean isLongPointerPressActionEnabled() {
        called("isLongPointerPressActionEnabled");
        return super.isLongPointerPressActionEnabled();
    }

    @Override
    public void setLongPointerPressActionEnabled(boolean longPointerPressAction) {
        called("setLongPointerPressActionEnabled");
        super.setLongPointerPressActionEnabled(longPointerPressAction);
    }

    @Override
    public void setRenderer(ListCellRenderer renderer) {
        called("setRenderer");
        super.setRenderer(renderer);
    }

    @Override
    public void setListCellRenderer(ListCellRenderer renderer) {
        called("setListCellRenderer");
        super.setListCellRenderer(renderer);
    }

    @Override
    public ListCellRenderer getRenderer() {
        debug("getRenderer");
        return super.getRenderer();
    }

    @Override
    public int getOrientation() {
        int result = super.getOrientation();
        debug("getOrientation=" + result);
        return result;
    }

    @Override
    public void refreshTheme(boolean merge) {
        called("refreshTheme");
        super.refreshTheme();
    }

    @Override
    public void setOrientation(int orientation) {
        called("setOrientation");
        super.setOrientation(orientation);
    }

    @Override
    public void scrollRectToVisible(Rectangle rect) {
        called("scrollRectToVisible");
        super.scrollRectToVisible(rect);
    }

    @Override
    public void setHandlesInput(boolean b) {
        called("setHandlesInput");
        super.setHandlesInput(b);
    }

    @Override
    protected void fireClicked() {
        called("fireClicked");
        super.fireClicked();
    }

    @Override
    protected boolean isSelectableInteraction() {
        called("isSelectableInteraction");
        return super.isSelectableInteraction();
    }

    @Override
    public void keyReleased(int keyCode) {
        called("keyReleased");
        super.keyReleased(keyCode);
    }

    @Override
    public void keyPressed(int keyCode) {
        called("keyPressed");
        super.keyPressed(keyCode);
    }

    @Override
    public void paint(Graphics g) {
        debug("paint");
        super.paint(g);
    }

    @Override
    protected boolean shouldRenderSelection() {
        boolean result = super.shouldRenderSelection();
        debug("shouldRenderSelection="+result);
        return result;
    }
    
    @Override
    public void addSelectionListener(SelectionListener l) {
        called("addSelectionListener");
        super.addSelectionListener(l);
    }

    @Override
    public void removeSelectionListener(SelectionListener l) {
        called("removeSelectionListener");
        super.removeSelectionListener(l);
    }

    @Override
    public void addActionListener(ActionListener listener) {
        called("addActionListener=" + listener);
        super.addActionListener(listener);
    }

    @Override
    public Vector getActionListeners() {
        called("getActionListeners");
        return super.getActionListeners();
    }

    @Override
    public Collection getListeners() {
        called("getListeners");
        return super.getListeners();
    }

    @Override
    public void removeActionListener(ActionListener l) {
        called("removeActionListener");
        super.removeActionListener(l);
    }

    @Override
    protected void fireActionEvent() {
        called("fireActionEvent");
        super.fireActionEvent();
    }

    @Override
    protected void fireActionEvent(ActionEvent event) {
        called("fireActionEvent(" + event + ")");
        super.fireActionEvent(event);
    }

    @Override
    public void setInputOnFocus(boolean inputOnFocus) {
        called("setInputOnFocus");
        super.setInputOnFocus(inputOnFocus);
    }

    @Override
    public void setPaintFocusBehindList(boolean paintFocusBehindList) {
        called("setPaintFocusBehindList");
        super.setPaintFocusBehindList(paintFocusBehindList);
    }

    @Override
    public int getItemGap() {
        int result = super.getItemGap();
        debug("getItemGap=" + result);
        return result;
    }

    @Override
    public void setItemGap(int itemGap) {
        called("setItemGap");
        super.setItemGap(itemGap);
    }

    @Override
    public void setRenderingPrototype(T renderingPrototype) {
        called("setRenderingPrototype");
        super.setRenderingPrototype(renderingPrototype);
    }

    @Override
    public T getRenderingPrototype() {
        called("getRenderingPrototype");
        return super.getRenderingPrototype();
    }

    @Override
    public void longPointerPress(int x, int y) {
        called("longPointerPress");
        super.longPointerPress(x, y);
    }

    @Override
    public void pointerPressed(int x, int y) {
        called("pointerPressed(" + x + "," + y + ")");
        super.pointerPressed(x, y);
    }

    @Override
    public void pointerHover(int[] x, int[] y) {
        called("pointerHover");
        super.pointerHover(x, y);
    }

    @Override
    public void pointerDragged(int x, int y) {
        debug("pointerDragged(" + x + "," + y + ")");
        super.pointerDragged(x, y);
    }

    @Override
    public Rectangle getSelectedRect() {
        called("getSelectedRect");
        return super.getSelectedRect();
    }

    @Override
    public void setFireOnClick(boolean fireOnClick){
        called("setFireOnClick");
        super.setFireOnClick(fireOnClick);
    }

    @Override
    public void pointerHoverReleased(int[] x, int[] y) {
        called("pointerHoverReleased");
        super.pointerHoverReleased(x, y);
    }

    @Override
    public void pointerReleased(int x, int y) {
        called("pointerReleased=(" + x + "," + y + ")");
        super.pointerReleased(x, y);
    }

    @Override
    protected Dimension calcPreferredSize() {
        Dimension result = super.calcPreferredSize();
        called("calcPreferredSize=" + result);
        return result;
    }

    @Override
    public void addItem(T item) {
        called("addItem");
        super.addItem(item);
    }

    @Override
    public int getFixedSelection() {
        called("getFixedSelection");
        return super.getFixedSelection();
    }

    @Override
    public void setFixedSelection(int fixedSelection) {
        called("setFixedSelection");
        super.setFixedSelection(fixedSelection);
    }

    @Override
    public boolean animate() {
        debug("animate");
        return super.animate();
    }

    @Override
    protected boolean isTactileTouch(int x, int y) {
        called("isTactileTouch");
        return super.isTactileTouch(x,y);
    }

    @Override
    public void setScrollToSelected(boolean scrollToSelected) {
        called("setScrollToSelected");
        super.setScrollToSelected(scrollToSelected);
    }

    @Override
    protected int getGridPosY() {
        called("getGridPosY");
        return super.getGridPosY();
    }

    @Override
    protected int getGridPosX() {
        called("getGridPosX");
        return super.getGridPosX();
    }

    @Override
    protected String paramString() {
        called("paramString");
        return super.paramString();
    }

    @Override
    public void setHint(String hint){
        called("setHint");
        super.setHint(hint);
    }

    @Override
    public String getHint() {
        called("getHint");
        return super.getHint();
    }

    @Override
    public void setHintIcon(Image icon){
        called("setHintIcon");
        super.setHintIcon(icon);
    }

    @Override
    public Image getHintIcon() {
        called("getHintIcon");
        return super.getHintIcon();
    }

    @Override
    public void setHint(String hint, Image icon){
        called("setHint");
        super.setHint(hint, icon);
    }

    private void debug(String method) {
        //called(method);
    }

    private void called(String method) {
        log(method);
    }
    
    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1DebugList.class);
    }

}
