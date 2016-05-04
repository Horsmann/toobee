package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsDot;

public class TestIsDot {

	@Test
	public void runTest(){
		assertTrue(IsDot.isDot("."));
		
		assertFalse(IsDot.isDot(","));
		assertFalse(IsDot.isDot("?"));
		assertFalse(IsDot.isDot("!"));
	}
}
