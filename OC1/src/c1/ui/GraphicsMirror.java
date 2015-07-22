package c1.ui;

import com.codename1.ui.*;
import com.codename1.ui.geom.Shape;
import x.util.AbstractMirror;

public final class GraphicsMirror
    extends AbstractMirror<Graphics>
{
    private static final String[] methodNames = {""};
    
    public GraphicsMirror() {
        super(Graphics.class,methodNames);
    }
    
    public Object invoke(String method, Object[] args) {
        if (method.equals("translate")) { target.translate(i(args[0]), i(args[1])); return null;}
        if (method.equals("getTranslateX")) { return target.getTranslateX();}
        if (method.equals("getTranslateY")) { return target.getTranslateY();}
        if (method.equals("getColor")) { return target.getColor();}
        if (method.equals("setColor")) { target.setColor(i(args[0])); return null;}
        if (method.equals("setAlpha")) { target.setAlpha(i(args[0])); return null;}
        if (method.equals("getFont")) { return target.getFont();}
        if (method.equals("getTransform")) { return target.getTransform();}
        if (method.equals("setFont")) { target.setFont(font(args[0])); return null;}
        if (method.equals("setTransform")) { target.setTransform(transform(args[0])); return null;}
        if (method.equals("getClipX")) { return target.getClipX();}
        if (method.equals("getClipY")) { return target.getClipY();}
        if (method.equals("getScaleX")) { return target.getScaleX();}
        if (method.equals("getScaleY")) { return target.getScaleY();}
        if (method.equals("getClipWidth")) { return target.getClipWidth();}
        if (method.equals("getClipHeight")) { return target.getClipHeight();}
        if (method.equals("getClip")) { return target.getClip();}
        if (method.equals("clipRect")) { target.clipRect(i(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("setClip")) { target.setClip(ia(args[0])); return null;}
        if (method.equals("setClip")) { target.setClip(i(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("pushClip")) { target.pushClip(); return null;}
        if (method.equals("popClip")) { target.popClip(); return null;}
        if (method.equals("drawLine")) { target.drawLine(i(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("fillRect")) { target.fillRect(i(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("fillRect")) { target.fillRect(i(args[0]),i(args[1]),i(args[2]),i(args[3]),by(args[4])); return null;}
        if (method.equals("drawRect")) { target.drawRect(i(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("drawRect")) { target.drawRect(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4])); return null;}
        if (method.equals("drawRoundRect")) { target.drawRoundRect(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("fillRoundRect")) { target.fillRoundRect(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("fillArc")) { target.fillArc(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("drawArc")) { target.drawArc(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("lighterColor")) { target.lighterColor(i(args[0])); return null;}
        if (method.equals("darkerColor")) { target.darkerColor(i(args[0])); return null;}
        if (method.equals("drawString")) { target.drawString(s(args[0]),i(args[1]),i(args[2])); return null;}
        if (method.equals("drawString")) { target.drawString(s(args[0]),i(args[1]),i(args[2]),i(args[3])); return null;}
        if (method.equals("drawChar")) { target.drawChar(c(args[0]),i(args[1]),i(args[2])); return null;}
        if (method.equals("drawChars")) { target.drawChars(ca(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4])); return null;}
        if (method.equals("drawImage")) { target.drawImage(image(args[0]),i(args[1]),i(args[2])); return null;}
        if (method.equals("drawShape")) { target.drawShape(shape(args[0]),stroke(args[1])); return null;}
        if (method.equals("fillShape")) { target.fillShape(shape(args[0])); return null;}
        if (method.equals("isTransformSupported")) { return target.isTransformSupported();}
        if (method.equals("isPerspectiveTransformSupported")) { return target.isPerspectiveTransformSupported();}
        if (method.equals("isShapeSupported")) { return target.isShapeSupported();}
        if (method.equals("fillTriangle")) { target.fillTriangle(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("fillRadialGradient")) { target.fillRadialGradient(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5])); return null;}
        if (method.equals("fillRectRadialGradient")) { target.fillRectRadialGradient(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5]),f(args[6]),f(args[7]),f(args[8])); return null;}
        if (method.equals("fillLinearGradient")) { target.fillLinearGradient(i(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4]),i(args[5]),b(args[6])); return null;}
        if (method.equals("fillPolygon")) { target.fillPolygon(ia(args[0]),ia(args[1]),i(args[2])); return null;}
        if (method.equals("drawPolygon")) { target.drawPolygon(ia(args[0]),ia(args[1]),i(args[2])); return null;}
        if (method.equals("isAlphaSupported")) { return target.isAlphaSupported();}
        if (method.equals("getAlpha")) { return target.getAlpha();}
        if (method.equals("isAntiAliasingSupported")) { return target.isAntiAliasingSupported();}
        if (method.equals("isAntiAliasedTextSupported")) { return target.isAntiAliasedTextSupported();}
        if (method.equals("isAntiAliased")) { return target.isAntiAliased();}
        if (method.equals("isAntiAliasedText")) { return target.isAntiAliasedText();}
        if (method.equals("isAffineSupported")) { return target.isAffineSupported();}
        if (method.equals("setAntiAliased")) { target.setAntiAliased(b(args[0])); return null;}
        if (method.equals("setAntiAliasedText")) { target.setAntiAliasedText(b(args[0])); return null;}
        if (method.equals("resetAffine")) { target.resetAffine(); return null;}
        if (method.equals("shear")) { target.shear(f(args[0]),f(args[1])); return null;}
        if (method.equals("scale")) { target.scale(f(args[0]),f(args[1])); return null;}
        if (method.equals("rotate")) { target.rotate(f(args[0])); return null;}
        if (method.equals("rotate")) { target.rotate(f(args[0]),i(args[1]),i(args[2])); return null;}
        if (method.equals("tileImage")) { target.tileImage(image(args[0]),i(args[1]),i(args[2]),i(args[3]),i(args[4])); return null;}
        throw methodNotFound(method,args);
    }
    
    private char[] ca(Object o) { return (char[]) o; }    
    private char c(Object o) { return (Character) o; }    
    private String s(Object o) { return (String) o; }    
    private int i(Object o) { return (Integer) o; }    
    private float f(Object o) { return (Float) o; }    
    private byte by(Object o) { return (Byte) o; }    
    private boolean b(Object o) { return (Boolean) o; }    
    private int[] ia(Object o) { return (int[]) o; }    
    private Font font(Object o) { return (Font) o; }    
    private Image image(Object o) { return (Image) o; }    
    private Shape shape(Object o) { return (Shape) o; }    
    private Stroke stroke(Object o) { return (Stroke) o; }    
    private Transform transform(Object o) { return (Transform) o; }    

}
