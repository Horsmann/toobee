package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsQuestionMark;

public class TestIsQuestionMark {

	@Test
	public void testIsQuestionMark(){
		assertTrue(IsQuestionMark.isQuestionMark("?"));
		
		assertFalse(IsQuestionMark.isQuestionMark(","));
		assertFalse(IsQuestionMark.isQuestionMark("!"));
		assertFalse(IsQuestionMark.isQuestionMark("."));
	}
}
