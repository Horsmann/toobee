package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsSpecialCharacter;

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
		
		assertFalse(IsSpecialCharacter.is("}!"));
		assertFalse(IsSpecialCharacter.is("a"));
		assertFalse(IsSpecialCharacter.is("1"));
	}
}
