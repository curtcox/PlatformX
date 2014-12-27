package common.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ObjectsTest {
    
    @Test
    public void areEqual_returns_true_if_arrays_are_equal() {
        assertTrue(Objects.areEqual(new Object[]{},new Object[]{}));
        assertTrue(Objects.areEqual(new Object[]{"a"},new Object[]{"a"}));
        assertTrue(Objects.areEqual(new Object[]{1,2,3},new Object[]{1,2,3}));
    }

        @Test
    public void areEqual_returns_false_if_arrays_are_not_equal() {
        assertFalse(Objects.areEqual(new Object[]{},new Object[]{""}));
        assertFalse(Objects.areEqual(new Object[]{"a"},new Object[]{"b"}));
        assertFalse(Objects.areEqual(new Object[]{1,2,3},new Object[]{1,2,4}));
    }

    @Test
    public void areEqual_returns_true_if_objects_are_both_null() {
        assertTrue(Objects.areEqual(null,null));
    }

    @Test
    public void areEqual_returns_true_if_are_equal() {
        assertTrue(Objects.areEqual("",""));
    }

    @Test
    public void areEqual_returns_false_if_are_not_equal() {
        assertFalse(Objects.areEqual("a","b"));
    }

    @Test
    public void areEqual_returns_false_if_only_one_is_null() {
        assertFalse(Objects.areEqual(null,""));
        assertFalse(Objects.areEqual("",null));
    }

}
