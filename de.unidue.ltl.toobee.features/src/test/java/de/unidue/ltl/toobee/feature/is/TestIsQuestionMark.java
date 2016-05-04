package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.is.IsQuestionMark;

public class TestIsQuestionMark {

	@Test
	public void runTest(){
		assertTrue(IsQuestionMark.isQuestionMark("?"));
		
		assertFalse(IsQuestionMark.isQuestionMark(","));
		assertFalse(IsQuestionMark.isQuestionMark("!"));
		assertFalse(IsQuestionMark.isQuestionMark("."));
	}
}
