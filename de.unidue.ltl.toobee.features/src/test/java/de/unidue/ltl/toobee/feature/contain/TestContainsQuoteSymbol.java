package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsQuoteSymbol
{

    @Test
    public void runTest()
    {
        assertTrue(ContainsQuoteSymbol.contains("ABC\"DE"));
        assertTrue(ContainsQuoteSymbol.contains("ABC'DE"));
        assertTrue(ContainsQuoteSymbol.contains("ABC``DE"));

        assertFalse(ContainsQuoteSymbol.contains("232"));
        assertFalse(ContainsQuoteSymbol.contains("abc"));
    }

}
