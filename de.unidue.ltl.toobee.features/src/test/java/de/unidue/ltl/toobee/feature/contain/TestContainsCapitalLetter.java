package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.contain.ContainsCapitalLetter;

public class TestContainsCapitalLetter {
	
	@Test
	public void testContainsCapitalLetter(){
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("ABCDE"));
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("abCde"));
		assertTrue(ContainsCapitalLetter.anyLetterCapitalized("23C2"));
		
		assertFalse(ContainsCapitalLetter.anyLetterCapitalized("232"));
		assertFalse(ContainsCapitalLetter.anyLetterCapitalized("abc"));
	}

}
