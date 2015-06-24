package x.pages;

import x.uiwidget.UIComponent;

final class CustomComponent
    extends UIComponent
{
    int px;
    int py;
    
//    @Override
//    public void paint(Graphics g) {
//        drawBackground(g);
//        drawPointer(g);
//    }
//
//    void drawBackground(Graphics g) {
//        g.setColor(Color.PINK.getRGB());
//        g.fillRect(0,0, getWidth()/2, getHeight()/2);
//        g.fillRect(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2);
//        g.setColor(Color.GREEN.getRGB());
//    }
//
//    void drawPointer(Graphics g) {
//        int r = 10;
//        int d = r * 2;
//        g.fillArc(px - r,py - r,d,d,0,360);
//    }
//
//    @Override
//    public void pointerPressed(int x, int y) {
//        recordPointer(x,y);
//    }
//
//    @Override
//    public void pointerDragged(int x, int y) {
//        recordPointer(x,y);
//    }
//
//    void recordPointer(int x, int y) {
//        int dx = getAbsoluteX() - getX();
//        int dy = getAbsoluteY() - getY();
//        px = x - dx;
//        py = y - dy;
//    }

}
