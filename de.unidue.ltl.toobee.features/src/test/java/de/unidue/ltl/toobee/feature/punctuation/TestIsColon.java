package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsColon;

public class TestIsColon {

	@Test
	public void testIsDot(){
		assertTrue(IsColon.isColon(":"));
		
		assertFalse(IsColon.isColon(","));
		assertFalse(IsColon.isColon("?"));
		assertFalse(IsColon.isColon("!"));
	}
}
