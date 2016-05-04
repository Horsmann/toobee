package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsClosingBracket {

	@Test
	public void runTest(){
		
		assertTrue(IsOpeningBracket.is("("));
		assertTrue(IsOpeningBracket.is("{"));
		assertTrue(IsOpeningBracket.is("["));

		assertFalse(IsOpeningBracket.is("]"));
		assertFalse(IsOpeningBracket.is("}"));
		assertFalse(IsOpeningBracket.is(")"));
		assertFalse(IsOpeningBracket.is(","));
		assertFalse(IsOpeningBracket.is("?"));
		assertFalse(IsOpeningBracket.is("!"));
	}
}
