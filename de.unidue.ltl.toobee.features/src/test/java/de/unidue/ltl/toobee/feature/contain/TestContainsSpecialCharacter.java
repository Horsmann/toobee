package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestContainsSpecialCharacter {

	@Test
	public void runTest(){
		assertTrue(ContainsSpecialCharacter.contains("?"));
		assertTrue(ContainsSpecialCharacter.contains(","));
		assertTrue(ContainsSpecialCharacter.contains("!"));
		assertTrue(ContainsSpecialCharacter.contains("."));
		assertTrue(ContainsSpecialCharacter.contains("¢"));
		assertTrue(ContainsSpecialCharacter.contains("{"));
		assertTrue(ContainsSpecialCharacter.contains("}"));
		
		assertFalse(ContainsSpecialCharacter.contains("a"));
		assertFalse(ContainsSpecialCharacter.contains("1"));
	}
}
