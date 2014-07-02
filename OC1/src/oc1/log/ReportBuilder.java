package oc1.log;

/**
 *
 * @author Curt
 */
final class ReportBuilder {

    final StringBuilder out = new StringBuilder();

    void add(String string) {
        out.append("\t" + string);
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
