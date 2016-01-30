package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsQuestionMark;

public class TestIsQuestionMark {

	@Test
	public void testIsQuestionMark(){
		assertTrue(IsQuestionMark.isQuestionMark("?"));
		
		assertFalse(IsQuestionMark.isQuestionMark(","));
		assertFalse(IsQuestionMark.isQuestionMark("!"));
		assertFalse(IsQuestionMark.isQuestionMark("."));
	}
}
