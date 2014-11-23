package oc1.ui;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;

public final class EmptyImage
    extends EncodedImage
{
    public EmptyImage(int width, int height) {
        super(width,height);
    }
    
    @Override protected void drawImage(Graphics g, Object nativeGraphics, int x, int y) {}
    @Override protected void drawImage(Graphics g, Object nativeGraphics, int x, int y, int w, int h) {}
}
