package de.unidue.ltl.toobee.feature.all;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.all.IsAllPunctuation;

public class TestIsAllPunctuation {

	@Test
	public void isPunctuation(){
		assertTrue(IsAllPunctuation.isAllPunctuation("?"));
		assertTrue(IsAllPunctuation.isAllPunctuation("!"));
		assertTrue(IsAllPunctuation.isAllPunctuation("."));
		assertTrue(IsAllPunctuation.isAllPunctuation("?!"));
		assertTrue(IsAllPunctuation.isAllPunctuation("???"));
		assertTrue(IsAllPunctuation.isAllPunctuation("..."));
		assertTrue(IsAllPunctuation.isAllPunctuation("!!!"));
		assertTrue(IsAllPunctuation.isAllPunctuation("!!!..,"));
	}
	
}
