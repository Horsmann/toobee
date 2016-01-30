package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsDot;

public class TestIsDot {

	@Test
	public void testIsDot(){
		assertTrue(IsDot.isDot("."));
		
		assertFalse(IsDot.isDot(","));
		assertFalse(IsDot.isDot("?"));
		assertFalse(IsDot.isDot("!"));
	}
}
