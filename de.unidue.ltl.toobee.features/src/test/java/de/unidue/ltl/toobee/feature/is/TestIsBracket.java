package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsBracket {

	@Test
	public void runTest(){
		assertTrue(IsBracket.is(")"));
		assertTrue(IsBracket.is("("));
		assertTrue(IsBracket.is("}"));
		assertTrue(IsBracket.is("{"));
		assertTrue(IsBracket.is("["));
		assertTrue(IsBracket.is("]"));
		
		assertFalse(IsBracket.is(","));
		assertFalse(IsBracket.is("?"));
		assertFalse(IsBracket.is("!"));
	}
}
