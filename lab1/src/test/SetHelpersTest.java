package test;

import static org.junit.Assert.*;

import org.junit.Test;

import set.SetHelpers;

import java.util.Arrays;

public class SetHelpersTest {
    @Test
    public final void testUniqueElements() {
        int[] a = {1, 3, 5, 1, 7, 2, 7, 1, 2, 1, 2, 3};
        int[] a_unique = {7, 5, 3, 2, 1};
        a = SetHelpers.uniqueElements(a);
        assertTrue(Arrays.equals(a, a_unique));
    }
}
