package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.*;

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
		
		assertFalse(IsSpecialCharacter.is("a"));
		assertFalse(IsSpecialCharacter.is("1"));
	}
}
