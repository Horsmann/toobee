package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsSpecialCharacter {

	@Test
	public void runTest(){
		assertTrue(IsSpecialCharacter.is("?"));
		assertTrue(IsSpecialCharacter.is(","));
		assertTrue(IsSpecialCharacter.is("!"));
		assertTrue(IsSpecialCharacter.is("."));
		assertTrue(IsSpecialCharacter.is("¢"));
		assertTrue(IsSpecialCharacter.is("{"));
		assertTrue(IsSpecialCharacter.is("}"));
	}
}
