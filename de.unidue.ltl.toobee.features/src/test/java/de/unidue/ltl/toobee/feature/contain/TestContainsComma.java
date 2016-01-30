package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.contain.ContainsCapitalLetter;

public class TestContainsComma {
	
	@Test
	public void testContainsCapitalLetter(){
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("ABC,DE"));
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("abC,de"));
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("23C,2"));
		
		assertFalse(ContainsCapitalLetter.anyLetterCapitalized("232"));
		assertFalse(ContainsCapitalLetter.anyLetterCapitalized("abc"));
	}

}
