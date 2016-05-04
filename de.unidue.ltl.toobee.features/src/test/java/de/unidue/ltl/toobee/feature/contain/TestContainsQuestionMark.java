package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsQuestionMark
{

    @Test
    public void runTest()
    {
        assertTrue(ContainsQuestionMark.contains("ABC?DE"));

        assertFalse(ContainsQuestionMark.contains("232"));
        assertFalse(ContainsQuestionMark.contains("abc"));
    }

}
