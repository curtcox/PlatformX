package org.robovm.apple.foundation;

import org.robovm.objc.ObjCObject;

public class NSObject extends ObjCObject implements NSObjectProtocol {
    private static RuntimeException never() {
        throw new RuntimeException();
    }
}
