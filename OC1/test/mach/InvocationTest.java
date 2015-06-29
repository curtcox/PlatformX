package mach;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class InvocationTest {

    Object proxy = new Object();
    Method method = proxy.getClass().getDeclaredMethods()[0];

    @Test
    public void can_create_with_empty_array() {
        Invocation invocation = new Invocation(proxy,method,args(),wild());

        assertSame(proxy,invocation.proxy);
        assertSame(method,invocation.method);
        assertEquals(0,invocation.args.size());
    }

    @Test
    public void can_create_with_null_wildcard_array() {
        new Invocation(proxy,method,args(),null);
        new Invocation(proxy,method,args(1),null);
        new Invocation(proxy,method,args(1,2),null);
    }

    @Test
    public void throws_IllegalArgumentException_when_more_args_than_wildcards() {
        try {
            new Invocation(proxy,method,args("x"),wild());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Must have the same number of arguments and wildcards",e.getMessage());
        }
    }

    @Test
    public void throws_IllegalArgumentException_when_more_wildcards_than_args() {
        try {
            new Invocation(proxy,method,args(),wild(""));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Must have the same number of arguments and wildcards",e.getMessage());
        }
    }

    @Test
    public void equal_values_are_equals_when_there_are_no_wildcards() {
        assertEqual(new Invocation(proxy, method, args(), null), new Invocation(proxy, method, args(), null));
        assertEqual(new Invocation(proxy, method, args(""), null), new Invocation(proxy, method, args(""), null));
        assertEqual(new Invocation(proxy, method, args(new ArrayList()), null), new Invocation(proxy, method, args(Collections.emptyList()), null));
    }

    @Test
    public void equal_values_are_equals_when_there_are_wildcards() {
        assertEquals(new Invocation(proxy,method,args("x"),wild("z")),new Invocation(proxy,method,args("x"),wild("y")));
    }

    @Test
    public void equal_values_are_equals_when_there_are_wildcards_in_unequal_spots() {
        assertEqual(new Invocation(proxy, method, args("x"), wild("x")), new Invocation(proxy, method, args("y"), null));
        assertEqual(new Invocation(proxy, method, args("x"), wild("x")), new Invocation(proxy, method, args("y"), wild("z")));
        assertEqual(new Invocation(proxy, method, args("f", "*", "x"), wild("", "*", "")), new Invocation(proxy, method, args("f", "o", "x"), wild("", "", "")));
    }

    private void assertEqual(Invocation a, Invocation b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void mock_equals_itself() {
        Map map = Mocks.mock("foo", Map.class);
        assertEqual(new Invocation(proxy, method, args(map), wild("")), new Invocation(proxy, method, args(map), wild("")));
    }

    @Test
    public void different_arguments_are_not_equal() {
        assertFalse(new Invocation(proxy, method,args(),null).equals(new Invocation(proxy, method,args(null),null)));
        assertFalse(new Invocation(proxy, method,args(0),null).equals(new Invocation(proxy, method,args(1),null)));
    }

    @Test
    public void different_proxy_is_not_equal() {
        Object different = new Object();
        assertFalse(new Invocation(proxy, method,args(),wild()).equals(new Invocation(different, method,args(),wild())));
    }

    @Test
    public void different_method_is_not_equal() {
        Method different = proxy.getClass().getDeclaredMethods()[1];
        assertFalse(new Invocation(proxy, method,args(),wild()).equals(new Invocation(proxy, different,args(),wild())));
    }

    @Test
    public void method_that_returns_void() throws NoSuchMethodException {
        Method void_method = Iterator.class.getMethod("remove");
        assertTrue(new Invocation(proxy,void_method,args(),wild()).returnsVoid());
    }

    @Test
    public void method_that_returns_primitive() throws NoSuchMethodException {
        Method void_method = Iterator.class.getMethod("hasNext");
        assertFalse(new Invocation(proxy,void_method,args(),wild()).returnsVoid());
    }

    @Test
    public void method_that_returns_object() throws NoSuchMethodException {
        Method void_method = Iterator.class.getMethod("next");
        assertFalse(new Invocation(proxy, void_method, args(), wild()).returnsVoid());
    }

    Object[] args(Object... args) {
        if (args==null) {
            return new Object[] {null};
        }
        return args;
    }

    Object[] wild(Object... args) {
        return args(args);
    }

}
