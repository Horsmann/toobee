package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestIsCamelCase {
	
	@Test
	public void testIsCamelCase(){
		assertTrue(IsCamelCase.isCamelCase("WiSu"));
		assertTrue(IsCamelCase.isCamelCase("iTunes"));
		
		assertFalse(IsCamelCase.isCamelCase("WiSO"));
	}

}
