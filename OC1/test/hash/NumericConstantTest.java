package hash;

import config.ShouldRun;
import org.junit.Before;
import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class NumericConstantTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void equals_returns_true_for_constants_with_the_same_values() {
        assertAreEqual(new NumericConstant(0),new NumericConstant(0));
        assertAreEqual(new NumericConstant(63130),new NumericConstant(63130));
    }

    @Test
    public void equals_returns_false_for_constants_with_different_values() {
        assertNotEquals(new NumericConstant(1),new NumericConstant(2));
    }

    private void assertAreEqual(NumericConstant a, NumericConstant b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertNotEquals(NumericConstant a, NumericConstant b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_costant() {
        assertTrue(Strings.contains(new NumericConstant(300).toString(),"300"));
    }

    @Test
    public void invokeIn_returns_constant_value() {
        assertEquals(7L,new NumericConstant(7).invokeIn(null));
        assertEquals(31415926L,new NumericConstant(31415926).invokeIn(null));
    }

}
