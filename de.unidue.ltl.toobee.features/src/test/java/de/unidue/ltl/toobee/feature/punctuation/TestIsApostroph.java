package de.unidue.ltl.toobee.feature.punctuation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsApostroph {

	@Test
	public void runTest(){
		assertTrue(IsApostroph.is("`"));
		assertTrue(IsApostroph.is("'"));
		
		assertFalse(IsApostroph.is("."));
		assertFalse(IsApostroph.is("?"));
		assertFalse(IsApostroph.is("!"));
	}
}
