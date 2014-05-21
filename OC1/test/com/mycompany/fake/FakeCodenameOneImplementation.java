package com.mycompany.fake;

import com.codename1.impl.CodenameOneImplementation;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Minimal fake implementation required for testing.
 * @author Curt
 */
final class FakeCodenameOneImplementation
    extends CodenameOneImplementation
{

    public FakeCodenameOneImplementation() {}

    @Override public void        init(Object m) {}
    @Override public Object getNativeGraphics() { return "Native Graphics?"; }
    @Override public int      getSoftkeyCount() { return 0; }
    @Override public int       getBackKeyCode() { return 0; }
    @Override public int  getBackspaceKeyCode() { return 0; }
    @Override public int      getClearKeyCode() { return 0; }
    @Override public boolean    isTouchDevice() { return false; }
    @Override public Object createFont(int face, int style, int size) { return "Font?"; }
    @Override public boolean storageFileExists(String name) { return false; }
    @Override public void deleteStorageFile(String name) {}
    @Override public int getDisplayWidth() { return 0; }
    @Override public int getDisplayHeight() { return 0; }
    @Override public OutputStream createStorageOutputStream(String name) throws IOException { return new ByteArrayOutputStream(); }
    @Override public void setClip(Object graphics, int x, int y, int width, int height) {}
    @Override public int charWidth(Object nativeFont, char ch) { return 0; }
    @Override public Object connect(String url, boolean read, boolean write) throws IOException { return new Object(); }
    @Override public int getHeight(Object nativeFont) { return 0; }
    @Override public void setPostRequest(Object connection, boolean p) {}
    @Override public int getClipX(Object graphics) { return 0; }
    @Override public int stringWidth(Object nativeFont, String str) { return 0; }
    @Override public int getClipY(Object graphics) { return 0; }
    @Override public void setHeader(Object connection, String key, String val) {}
    
    // Stuff we needed to minimally do above.
    // Stuff we just throw exceptions for below.

    @Override
    public void editString(Component cmp, int maxSize, int constraint, String text, int initiatingKeycode) {
        throw never();
    }

    @Override
    public void flushGraphics(int x, int y, int width, int height) {
        throw never();
    }

    @Override
    public void flushGraphics() {
        throw never();
    }

    @Override
    public void getRGB(Object nativeImage, int[] arr, int offset, int x, int y, int width, int height) {
        throw never();
    }

    @Override
    public Object createImage(int[] rgb, int width, int height) {
        throw never();
    }

    @Override
    public Object createImage(String path) throws IOException {
        throw never();
    }

    @Override
    public Object createImage(InputStream i) throws IOException {
        throw never();
    }

    @Override
    public Object createMutableImage(int width, int height, int fillColor) {
        throw never();
    }

    @Override
    public Object createImage(byte[] bytes, int offset, int len) {
        throw never();
    }

    @Override
    public int getImageWidth(Object i) {
        throw never();
    }

    @Override
    public int getImageHeight(Object i) {
        throw never();
    }

    @Override
    public Object scale(Object nativeImage, int width, int height) {
        throw never();
    }

    @Override
    public int[] getSoftkeyCode(int index) {
        throw never();
    }

    @Override
    public int getGameAction(int keyCode) {
        throw never();
    }

    @Override
    public int getKeyCode(int gameAction) {
        throw never();
    }

    @Override
    public int getColor(Object graphics) {
        throw never();
    }

    @Override
    public void setColor(Object graphics, int RGB) {
        throw never();
    }

    @Override
    public void setAlpha(Object graphics, int alpha) {
        throw never();
    }

    @Override
    public int getAlpha(Object graphics) {
        throw never();
    }

    @Override
    public void setNativeFont(Object graphics, Object font) {
        throw never();
    }

    @Override
    public int getClipWidth(Object graphics) {
        throw never();
    }

    @Override
    public int getClipHeight(Object graphics) {
        throw never();
    }

    @Override
    public void clipRect(Object graphics, int x, int y, int width, int height) {
        throw never();
    }

    @Override
    public void drawLine(Object graphics, int x1, int y1, int x2, int y2) {
        throw never();
    }

    @Override
    public void fillRect(Object graphics, int x, int y, int width, int height) {
        throw never();
    }

    @Override
    public void drawRect(Object graphics, int x, int y, int width, int height) {
        throw never();
    }

    @Override
    public void drawRoundRect(Object graphics, int x, int y, int width, int height, int arcWidth, int arcHeight) {
        throw never();
    }

    @Override
    public void fillRoundRect(Object graphics, int x, int y, int width, int height, int arcWidth, int arcHeight) {
        throw never();
    }

    @Override
    public void fillArc(Object graphics, int x, int y, int width, int height, int startAngle, int arcAngle) {
        throw never();
    }

    @Override
    public void drawArc(Object graphics, int x, int y, int width, int height, int startAngle, int arcAngle) {
        throw never();
    }

    @Override
    public void drawString(Object graphics, String str, int x, int y) {
        throw never();
    }

    @Override
    public void drawImage(Object graphics, Object img, int x, int y) {
        throw never();
    }

    @Override
    public void drawRGB(Object graphics, int[] rgbData, int offset, int x, int y, int w, int h, boolean processAlpha) {
        throw never();
    }

    @Override
    public Object getNativeGraphics(Object image) {
        throw never();
    }

    @Override
    public int charsWidth(Object nativeFont, char[] ch, int offset, int length) {
        throw never();
    }

    @Override
    public Object getDefaultFont() {
        throw never();
    }

    @Override
    public int getContentLength(Object connection) {
        throw never();
    }

    @Override
    public OutputStream openOutputStream(Object connection) throws IOException {
        throw never();
    }

    @Override
    public OutputStream openOutputStream(Object connection, int offset) throws IOException {
        throw never();
    }

    @Override
    public InputStream openInputStream(Object connection) throws IOException {
        throw never();
    }

    @Override
    public int getResponseCode(Object connection) throws IOException {
        throw never();
    }

    @Override
    public String getResponseMessage(Object connection) throws IOException {
        throw never();
    }

    @Override
    public String getHeaderField(String name, Object connection) throws IOException {
        throw never();
    }

    @Override
    public String[] getHeaderFieldNames(Object connection) throws IOException {
        throw never();
    }

    @Override
    public String[] getHeaderFields(String name, Object connection) throws IOException {
        throw never();
    }

    @Override
    public InputStream createStorageInputStream(String name) throws IOException {
        throw never();
    }

    @Override
    public String[] listStorageEntries() {
        throw never();
    }

    @Override
    public String[] listFilesystemRoots() {
        throw never();
    }

    @Override
    public String[] listFiles(String directory) throws IOException {
        throw never();
    }

    @Override
    public long getRootSizeBytes(String root) {
        throw never();
    }

    @Override
    public long getRootAvailableSpace(String root) {
        throw never();
    }

    @Override
    public void mkdir(String directory) {
        throw never();
    }

    @Override
    public void deleteFile(String file) {
        throw never();
    }

    @Override
    public boolean isHidden(String file) {
        throw never();
    }

    @Override
    public void setHidden(String file, boolean h) {
        throw never();
    }

    @Override
    public long getFileLength(String file) {
        throw never();
    }

    @Override
    public boolean isDirectory(String file) {
        throw never();
    }

    @Override
    public boolean exists(String file) {
        throw never();
    }

    @Override
    public void rename(String file, String newName) {
        throw never();
    }

    @Override
    public char getFileSystemSeparator() {
        throw never();
    }

    @Override
    public String getPlatformName() {
        throw never();
    }

    @Override
    public L10NManager getLocalizationManager() {
        throw never();
    }

    private static RuntimeException never() {
        throw new RuntimeException("Not supported yet.");
    }
    
}
