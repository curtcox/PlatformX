package x.uiwidget;

import x.ui.AttributedString;

/**
 * Skeletal implementation of text that does something when you tap it.
 * Implementers must provide onTap.
 * @author Curt
 */
public abstract class XAttributedText
    extends XComponent
{
    public AttributedString text;

    public static class SelectedEvent {

        public final AttributedString text;
        public final int part;

        public SelectedEvent(AttributedString text, int part) {
            this.text = text;
            this.part = part;
        }

        public String toString() {
            return "part=" + part;
        }
    }

    public XAttributedText(AttributedString text) {
        this.text = text;
    }

    /**
     * Override this method to perform the button tap action.
     */
    public abstract void onTap(SelectedEvent event);

    @Override
    public String toString() {
        return "AttributedText:" + text;
    }

    public String getText() {
        return text.toString();
    }

}
