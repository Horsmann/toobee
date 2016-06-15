package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsTripleDot {

	@Test
	public void runTest(){
		assertTrue(IsTripleDot.isTrippleDot("..."));
		assertTrue(IsTripleDot.isTrippleDot("…"));
		
		assertFalse(IsTripleDot.isTrippleDot(".."));
	}
}
