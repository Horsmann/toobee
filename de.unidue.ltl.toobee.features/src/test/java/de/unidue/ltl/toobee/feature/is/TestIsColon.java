package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsColon;

public class TestIsColon {

	@Test
	public void runTest(){
		assertTrue(IsColon.isColon(":"));
		
		assertFalse(IsColon.isColon(","));
		assertFalse(IsColon.isColon("?"));
		assertFalse(IsColon.isColon("!"));
	}
}
