package de.unidue.ltl.toobee.feature.all;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.all.IsAllNonPunctuationSpecialChars;

public class TestIsAllNonPunctuationSpecialChars {

	@Test
	public void testAllNonPunctuationSpecialChars(){
		assertTrue(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar("<--"));
		assertTrue(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar("/"));
		assertTrue(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar("+"));
		assertTrue(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar("*"));
		
		assertFalse(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar(":)"));
		assertFalse(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar(":P"));
		assertFalse(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar(":-P"));
		assertFalse(IsAllNonPunctuationSpecialChars.isNonPuncSpecialChar("(-8"));
	}
	
}
