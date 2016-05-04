package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsExclamationMark;

public class TestIsExclamationMark {

	@Test
	public void runTest(){
		assertTrue(IsExclamationMark.isExclamationMark("!"));
		
		assertFalse(IsExclamationMark.isExclamationMark(","));
		assertFalse(IsExclamationMark.isExclamationMark("?"));
		assertFalse(IsExclamationMark.isExclamationMark("."));
	}
}
