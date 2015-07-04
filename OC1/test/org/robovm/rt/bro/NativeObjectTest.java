/*
 * Copyright (C) 2014 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.rt.bro;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
 * Tests {@link NativeObject}.
 */
public class NativeObjectTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    static class A extends NativeObject {
        A(long h) { setHandle(h); }
    }
    static class B extends NativeObject {
        B(long h) { setHandle(h); }
    }
    static class C extends B {
        C(long h) { super(h); }
    }

    @Test
    public void test_A_As_B() {
        A a = new A(123);
        B b = a.as(B.class);
        assertEquals(B.class, b.getClass());
        assertEquals(a.getHandle(), b.getHandle());
    }

    @Test
    public void test_B_as_C() {
        B b = new B(1234);
        C c = b.as(C.class);
        assertEquals(C.class, c.getClass());
        assertEquals(b.getHandle(), c.getHandle());
        assertTrue(c == c.as(C.class));
        assertTrue(c == c.as(B.class));
    }

    @Test
    public void test_A_as_B_as_C() {
        A a = new A(123);
        B b = a.as(B.class);
        C c = b.as(C.class);
        assertEquals(C.class, c.getClass());
        assertEquals(b.getHandle(), c.getHandle());
        assertTrue(c == c.as(C.class));
        assertTrue(c == c.as(B.class));
    }

}