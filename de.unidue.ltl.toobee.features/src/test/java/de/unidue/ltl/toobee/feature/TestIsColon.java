package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsColon;

public class TestIsColon {

	@Test
	public void testIsDot(){
		assertTrue(IsColon.isColon(":"));
		
		assertFalse(IsColon.isColon(","));
		assertFalse(IsColon.isColon("?"));
		assertFalse(IsColon.isColon("!"));
	}
}
