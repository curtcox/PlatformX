package org.robovm.apple.foundation;

public class NSIndexPath extends NSObject {

    private final long item;
    private final long section;

    NSIndexPath(long item, long section) {
        this.item = item;
        this.section = section;
    }

    public static NSIndexPath createWithItem(long item, long section) {
        return new NSIndexPath(item, section);
    }

    public int getItem() {
        return (int) item;
    }

    public int getSection() {
        return (int) section;
    }
}