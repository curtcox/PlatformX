package google;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Thanks Sihan http://sihantech.wordpress.com/2008/07/16/encoding-url-in-j2me/
 */
final class URLEncoder {

    
    
    public static String encode(String s) {
        StringBuffer out = new StringBuffer();
        for (byte c : stringToBytes(s)) {            
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '*' || c == '_') {
                out.append((char) c);
            } else if (c == ' ') {
                out.append('+');
            } else {
                appendHex(c, out);
            }
        }
        return out.toString();
    }

    private static byte[] stringToBytes(String string) {
        return string.getBytes();
    }
    
    private static void appendHex(int value, StringBuffer buff) {
        buff.append('%');
        if (value < 16) {
            buff.append('0');
        }
        buff.append(Integer.toHexString(value));
    }
}
