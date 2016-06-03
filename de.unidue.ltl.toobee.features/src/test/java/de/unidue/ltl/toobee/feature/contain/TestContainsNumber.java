package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsNumber
{

    @Test
    public void runTest()
    {
        assertTrue(ContainsNumber.contains("ABC1DE"));
        assertTrue(ContainsNumber.contains("ABCDE3"));
        assertTrue(ContainsNumber.contains("ABC0DE"));
        assertTrue(ContainsNumber.contains("ABC1D9E"));

        assertFalse(ContainsQuoteSymbol.contains("abc"));
    }

}
