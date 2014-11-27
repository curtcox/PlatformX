package oc1.log;

/**
 * For building a plain-text report.
 * @author Curt
 */
final class ReportBuilder {

    final StringBuilder out = new StringBuilder();

    void add(String note) {
        out.append("\t" + note);
        out.append("\r\n");
    }

    void add(String key, Object value) {
        out.append("\t" + key + "=" + value);
        out.append("\r\n");
    }

    void section(String string) {
        out.append(string);
        out.append("\r\n");
    }

    void heading(String string) {
        out.append(string);
        out.append("\r\n");
    }

    @Override
    public String toString() {
        return out.toString();
    }
}
