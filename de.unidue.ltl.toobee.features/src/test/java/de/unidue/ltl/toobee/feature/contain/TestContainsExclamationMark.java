package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsExclamationMark
{

    @Test
    public void testContainsCapitalLetter()
    {
        assertTrue(ContainsExclamationMark.contains("ABC!DE"));

        assertFalse(ContainsExclamationMark.contains("232"));
        assertFalse(ContainsExclamationMark.contains("abc"));
    }

}
