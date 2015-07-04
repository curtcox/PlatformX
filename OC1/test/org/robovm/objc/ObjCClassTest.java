package org.robovm.objc;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class ObjCClassTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void can_create_from_object_handle() {
        long handle = 0;
        assertNotNull(ObjCClass.getFromObject(handle));
    }

    @Test
    public void getFromObject_returns_ObjCClass() {
        long handle = 42;
        boolean customClass = true;
        ObjCObject id = new ObjCObject(handle,customClass) {};
        ObjCClass actual = ObjCClass.getFromObject(id);

        assertNotNull(actual);
    }

//    public static ObjCClass toObjCClass(long objCClassHandle) {
//        long classPtr = objCClassHandle;
//        ObjCClass c = ObjCObject.getPeerObject(objCClassHandle);
//        if(c == null) {
//            c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(objCClassHandle)));
//        }
//
//        while(c == null && classPtr != 0L) {
//            classPtr = ObjCRuntime.class_getSuperclass(classPtr);
//            c = ObjCObject.getPeerObject(classPtr);
//            if(c == null) {
//                c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(classPtr)));
//            }
//        }
//
//        if(c == null) {
//            String name = VM.newStringUTF(ObjCRuntime.class_getName(objCClassHandle));
//            throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + name);
//        } else {
//            return c;
//        }
//    }
}