package x.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class ObjectsTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void areEqual_returns_true_if_arrays_are_equal() {
        assertTrue(Objects.areEqual(new Object[]{},        new Object[]{}));
        assertTrue(Objects.areEqual(new Object[]{null},    new Object[]{null}));
        assertTrue(Objects.areEqual(new Object[]{x("")},   new Object[]{x("")}));
        assertTrue(Objects.areEqual(new Object[]{"a"},     new Object[]{"a"}));
        assertTrue(Objects.areEqual(new Object[]{1, 2, 3}, new Object[]{1, 2, 3}));
    }

    @Test
    public void areEqual_returns_false_if_arrays_are_not_equal() {
        assertFalse(Objects.areEqual(new Object[]{},        new Object[]{""}));
        assertFalse(Objects.areEqual(new Object[]{null},    new Object[]{""}));
        assertFalse(Objects.areEqual(new Object[]{null},    new Object[]{x("")}));
        assertFalse(Objects.areEqual(new Object[]{x("")},   new Object[]{null}));
        assertFalse(Objects.areEqual(new Object[]{"a"},     new Object[]{"b"}));
        assertFalse(Objects.areEqual(new Object[]{x("a")},  new Object[]{x("b")}));
        assertFalse(Objects.areEqual(new Object[]{1, 2, 3}, new Object[]{1, 2, 4}));
    }

    @Test
    public void areEqual_returns_true_if_lists_are_equal() {
        assertTrue(Objects.areEqual(list(), list()));
        assertTrue(Objects.areEqual(list(null), list(null)));
        assertTrue(Objects.areEqual(list("a"), list("a")));
        assertTrue(Objects.areEqual(list(x("a")), list(x("a"))));
        assertTrue(Objects.areEqual(list(1, 2, 3), list(1, 2, 3)));
    }

    @Test
    public void areEqual_returns_false_if_lists_are_not_equal() {
        assertFalse(Objects.areEqual(list(),        list("")));
        assertFalse(Objects.areEqual(list(null),    list("")));
        assertFalse(Objects.areEqual(list(null),    list(x(""))));
        assertFalse(Objects.areEqual(list(x("")),   list(null)));
        assertFalse(Objects.areEqual(list("a"),     list("b")));
        assertFalse(Objects.areEqual(list(x("a")),  list(x("b"))));
        assertFalse(Objects.areEqual(list(1, 2, 3), list(1, 2, 4)));
    }

    private List list() {
        return new ArrayList();
    }

    private List list(Object a) {
        List list = new ArrayList();
        list.add(a);
        return list;
    }

    private List list(Object a, Object b) {
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        return list;
    }

    private List list(Object a, Object b, Object c) {
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        return list;
    }

    @Test
    public void areEqual_returns_true_if_objects_are_both_null() {
        assertTrue(Objects.areEqual(null, null));
    }

    @Test
    public void areEqual_returns_true_if_are_equal() {
        assertTrue(Objects.areEqual("",""));
    }

    @Test
    public void areEqual_returns_false_if_are_not_equal() {
        assertFalse(Objects.areEqual("a", "b"));
    }

    @Test
    public void areEqual_returns_false_if_only_one_is_null() {
        assertFalse(Objects.areEqual(null,""));
        assertFalse(Objects.areEqual("",null));
    }

    static class X {
        final Object value;
        X(Object value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object o) {
            // Despite "Effective Java" this is the form of equals implementation I usually want.
            X that = (X) o;
            return value.equals(that.value);
        }
    }

    private X x(final String value) {
        return new X(value);
    }

}
