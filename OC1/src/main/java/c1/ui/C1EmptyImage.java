package c1.ui;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;

public final class C1EmptyImage
    extends EncodedImage
{
    public C1EmptyImage(int width, int height) {
        super(width,height);
    }
    
    @Override protected void drawImage(Graphics g, Object nativeGraphics, int x, int y) {}
    @Override protected void drawImage(Graphics g, Object nativeGraphics, int x, int y, int w, int h) {}
}
