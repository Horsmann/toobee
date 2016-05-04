package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsUnderscore
{

    @Test
    public void runTest()
    {
        assertTrue(ContainsUnderScore.contains("ABC_DE"));

        assertFalse(ContainsUnderScore.contains("232"));
        assertFalse(ContainsUnderScore.contains("abc"));
    }

}
