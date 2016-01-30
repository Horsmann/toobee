package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsExclamationMark;

public class TestIsExclamationMark {

	@Test
	public void testIsExclamationMark(){
		assertTrue(IsExclamationMark.isExclamationMark("!"));
		
		assertFalse(IsExclamationMark.isExclamationMark(","));
		assertFalse(IsExclamationMark.isExclamationMark("?"));
		assertFalse(IsExclamationMark.isExclamationMark("."));
	}
}
