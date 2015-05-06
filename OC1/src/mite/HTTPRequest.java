package mite;

import java.util.StringTokenizer;

public final class HTTPRequest {

    public final String string;
    public final String method;
    public final String filename;
    public final boolean MIMEAware;

    public static HTTPRequest parse(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        String method = tokenizer.nextToken();
        String filename = tokenizer.nextToken();
        return new HTTPRequest(string,method,filename,HTTPVersion.isMIMEAware(string));
    }

    private HTTPRequest(String string, String method, String filename, boolean MIMEAware) {
        this.string = string;
        this.method = method;
        this.filename = filename;
        this.MIMEAware = MIMEAware;
    }
}
