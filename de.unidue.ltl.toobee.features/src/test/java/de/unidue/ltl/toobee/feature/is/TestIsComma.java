package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.punctuation.IsComma;

public class TestIsComma {

	@Test
	public void runTest(){
		assertTrue(IsComma.is(","));
		
		assertFalse(IsComma.is("."));
		assertFalse(IsComma.is("?"));
		assertFalse(IsComma.is("!"));
	}
}
