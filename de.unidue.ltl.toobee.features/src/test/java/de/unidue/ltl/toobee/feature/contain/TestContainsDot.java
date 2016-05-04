package de.unidue.ltl.toobee.feature.contain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContainsDot {
	
	@Test
	public void runTest(){
		assertTrue(ContainsDot.contains("ABC.DE"));
		
		assertFalse(ContainsDot.contains("232"));
		assertFalse(ContainsDot.contains("abc"));
	}

}
