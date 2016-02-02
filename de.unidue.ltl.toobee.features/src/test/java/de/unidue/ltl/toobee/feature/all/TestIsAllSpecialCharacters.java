package de.unidue.ltl.toobee.feature.all;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.all.IsAllSpecialCharacters;

public class TestIsAllSpecialCharacters {

	@Test
	public void isPunctuation(){
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("?"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("!"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("."));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("?!"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("???"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("..."));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("!!!"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("!!!..,"));
		assertTrue(IsAllSpecialCharacters.isAllPunctuation("<-"));
	}
	
}
